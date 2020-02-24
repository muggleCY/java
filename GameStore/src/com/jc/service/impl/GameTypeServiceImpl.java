package com.jc.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.GameDao;
import com.jc.dao.GameTypeDao;
import com.jc.exception.GameTypeDelErrorException;
import com.jc.exception.GameTypeExistException;
import com.jc.pojo.Game;
import com.jc.pojo.GameType;
import com.jc.service.GameTypeService;
import com.jc.util.FileUtil;

@Service
public class GameTypeServiceImpl implements GameTypeService {
	@Autowired
	private GameTypeDao gameTypeDao;
	@Autowired
	private GameDao gameDao;

	// 获取所有游戏类型
	@Override
	public List<GameType> getGameTypes(GameType gameType) {
		return gameTypeDao.selectGameTypes(gameType);
	}

	@Override
	@Transactional
	public void addGameType(GameType gameType,InputStream uploadFile,String imgPath, String fileName) throws Exception {
		String filePath = imgPath + "\\" + fileName;
		Date date = new Date();
		try {
			List<GameType> newGameType = gameTypeDao.selectGameTypeByName(gameType);
			if (newGameType.size() != 0) {
				throw new GameTypeExistException(Constants.GAMETYPE_EXIST);
			}
			// 数据库保存
			gameType.setTypePicture(fileName);
			gameType.setCreateTime(date);
			gameType.setUpdateTime(date);
			gameTypeDao.insertGameType(gameType);
			// 文件上传
			fileName = gameType.getId()+fileName;
			filePath = imgPath + "\\" + fileName;
			FileUtil.uploadFile(uploadFile, filePath);
			gameType.setTypePicture(fileName);
			gameTypeDao.updateGameType(gameType);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public GameType getGameTypeById(GameType gameType) {
		return gameTypeDao.selectGameTypeById(gameType);
	}

	@Override
	@Transactional
	public void modifyGameType(GameType gameType) throws Exception {
		List<GameType> newGameType = gameTypeDao.selectGameTypeByName(gameType);
		GameType gameType1 = gameTypeDao.selectGameTypeById(gameType);
		if (newGameType.size() == 1 &&!gameType.getTypeName().equals(gameType1.getTypeName())) {
			throw new GameTypeExistException(Constants.GAMETYPE_EXIST);
		}
		Date date = new Date();
		gameType.setUpdateTime(date);
		gameTypeDao.updateGameType(gameType);
	}

	@Override
	public void removeGameType(String[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
				List<Game> games =  gameDao.seleteGamesByGameType(Integer.parseInt(ids[i]));
				if(games.size() !=0){
					throw new GameTypeDelErrorException("游戏类型下存在游戏");
				}
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				GameType gameType = new GameType();
				gameType.setId(Integer.parseInt(ids[i]));
				gameType.setTypeStatus(3);
				gameTypeDao.updateGameType(gameType);
//				gameTypeDao.deleteGameType(Integer.parseInt(ids[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(Constants.GAMETYPE_DELETE_ERROR);
		}
	}

//	// 根据游戏数量排序，获取所有游戏类型
//	@Transactional
//	public List<GameType> getAllGameTypesByGameCount() {
//		return gameTypeDao.selectAllGameTypesByGameCount();
//	}
//
//	// 修改游戏类型状态
//	@Transactional
//	public void modifyGameTypeStatus(String name, String typeStatus,
//			Date updateTime) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", name);
//		map.put("typeStatus", typeStatus);
//		map.put("updateTime", updateTime);
//		gameTypeDao.updateGameTypeStatus(map);
//	}
//
//	// 删除游戏类型
//	@Transactional
//	public void removeGameType(String[] delId, String serverPicturePath)
//			throws GameTypeContainsGamesException {
//		// 根据类型id查询游戏类型
//		List<GameType> list = gameTypeDao.selectGameByIds(delId);
//		for (int i = 0; i < list.size(); i++) {
//			String name = list.get(i).getName();
//			List<Game> games = gameDao.selectGameByType(name);
//			if (games.size() != 0) {
//				throw new GameTypeContainsGamesException("要删除的游戏类型下，包含游戏，无法删除");
//			}
//		}
//		for (GameType gameType : list) {
//			// 根据游戏类型中，保存的图片路径，删除服务器的图片
//			new File(serverPicturePath.concat(gameType.getPicture())).delete();
//		}
//		gameTypeDao.deleteGameType(delId);
//	}
//
//	// 添加游戏类型
//	@Transactional
//	public void addGameType(String name, String status, InputStream uploadFile,
//			String imgPath, String fileName) throws UploadTypeException,
//			GameTypeExistedException {
//		String filePath = imgPath + "\\" + fileName;
//		Date date = new Date();
//		try {
//			GameType gameType = gameTypeDao.selectGameTypeByName(name);
//			if (gameType != null) {
//				throw new GameTypeExistedException("该游戏类型名已存在");
//			}
//			// 文件上传
//			FileUtil.uploadFile(uploadFile, filePath);
//			// 数据库保存
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("name", name);
//			map.put("typeStatus", status);
//			// map.put("filePath", "images\\uploadImages\\" + fileName);
//map.put("filePath", "images\\type\\" + fileName);
//			map.put("createTime", date);
//			map.put("updateTime", date);
//			gameTypeDao.insertGameType(map);
//		} catch (UploadTypeException e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
}

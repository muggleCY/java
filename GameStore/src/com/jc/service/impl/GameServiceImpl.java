package com.jc.service.impl;


import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.GameDao;
import com.jc.exception.GameAlreadyExistsException;
import com.jc.exception.GameDelErrorException;
import com.jc.pojo.Game;
import com.jc.service.GameService;
import com.jc.util.FileUtil;
import com.jc.util.Pager;
import com.jc.vo.GameVo;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	private GameDao gameDao;
//	@Autowired
//	private GameTypeDao gameTypeDao;
	/**
	 * 查找所有
	 */
	@Override
	@Transactional
	public Pager<Game> selectGamesByPage(Integer pageNo, String gameName,
			String gameType) {
		
		Pager<Game> pager = new Pager<Game>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("gameName", gameName);
		map.put("gameType", gameType);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		//找条数
		Integer totalCount = gameDao.countGame(map);
		//找数据
		List<Game> games = gameDao.selectGamesByPage(map);
		
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(games);
		return pager;
	}
	@Override
	@Transactional
	public Pager<GameVo> selectGames(Integer pageNo, String gameName,
			String gameType) {
		
		Pager<GameVo> pager = new Pager<GameVo>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("gameName", gameName);
		map.put("gameType", gameType);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		//找条数
		Integer totalCount = gameDao.countGame(map);
		//找数据
		List<GameVo> games = gameDao.selectGames(map);
		
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(games);
		return pager;
	}
	@Override
	@Transactional
	public Pager<GameVo> selectGamesForUser(Integer pageNo, String gameName,
			String gameType) {
		
		Pager<GameVo> pager = new Pager<GameVo>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("gameName", gameName);
		map.put("gameType", gameType);
		map.put("typeStatus", 1);
		map.put("gameStatus", 1);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		//找条数
		Integer totalCount = gameDao.countGameForUser(map);
		//找数据
		List<GameVo> games = gameDao.selectGames(map);
		
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(games);
		return pager;
	}
	/**
	 * 查找一个游戏
	 */
	@Override
	public Game selectGame(Integer id){
		return gameDao.selectGame(id);
	}

	@Override
	@Transactional
	public void addOrModifyGame(Game game,InputStream coverFile, InputStream screen1File,
			InputStream screen2File, InputStream screen3File,  String coverPath,String screenPath,
			String coverName,String screen1Name,String screen2Name,String screen3Name)
			throws Exception {
		System.out.println(screen1File);
		System.out.println(screen2File+"+++++++");
		System.out.println(screen3File);
		Date date = new Date();
		if(game.getId() == null){
			//新增
			Game newGame = gameDao.selectGameByName(game);
			if(newGame != null){
				throw new GameAlreadyExistsException(Constants.GAME_EXIST);
			}
			game.setGameCover(coverName);
			game.setGameScreen1(screen1Name);
			game.setGameScreen2(screen2Name);
			game.setGameScreen3(screen3Name);
			game.setCreateTime(date);
			game.setUpdateTime(date);
			gameDao.insertGame(game);
			coverName = game.getId()+coverName;
			screen1Name = game.getId()+screen1Name;
			screen2Name = game.getId()+screen2Name;
			screen3Name = game.getId()+screen3Name;
			String filePath1 = coverPath + "\\" + coverName;
			String filePath2 = screenPath + "\\" + screen1Name;
			String filePath3 = screenPath + "\\" + screen2Name;
			String filePath4 = screenPath + "\\" + screen3Name;
			try {
				FileUtil.uploadFile(coverFile,filePath1);
				FileUtil.uploadFile(screen1File,filePath2);
				FileUtil.uploadFile(screen2File,filePath3);
				FileUtil.uploadFile(screen3File,filePath4);
				game.setGameCover(coverName);
				game.setGameScreen1(screen1Name);
				game.setGameScreen2(screen2Name);
				game.setGameScreen3(screen3Name);
				gameDao.updateGame(game);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else{
			//修改
			Game newGame = gameDao.selectGameByName(game);
			if(newGame != null&&!newGame.getGameName().equals(game.getGameName())){
				throw new GameAlreadyExistsException(Constants.GAME_EXIST);
			}
			try {
				if(coverName != null){
					coverName = game.getId()+coverName;
					String filePath1 = coverPath + "\\" + coverName;
					FileUtil.uploadFile(coverFile,filePath1);
					game.setGameCover(coverName);
				}
				if(screen1Name != null){
					screen1Name = game.getId()+screen1Name;
					String filePath2 = screenPath + "\\" + screen1Name;
					FileUtil.uploadFile(screen1File,filePath2);
					game.setGameScreen1(screen1Name);
				}
				if(screen2Name != null){
					screen2Name = game.getId()+screen2Name;
					String filePath3 = screenPath + "\\" + screen2Name;
					FileUtil.uploadFile(screen2File,filePath3);
					game.setGameScreen2(screen2Name);
				}
				if(screen3Name != null){
					screen3Name = game.getId()+screen3Name;
					String filePath4 = screenPath + "\\" + screen3Name;
					FileUtil.uploadFile(screen3File,filePath4);
					game.setGameScreen3(screen3Name);
				}
				game.setUpdateTime(date);
				gameDao.updateGame(game);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
	}

	
	@Override
	@Transactional
	public void removeGame(String[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			try {
				Game game = new Game();
				game.setId(Integer.parseInt(ids[i]));
				game.setGameStatus(3);
				gameDao.updateGame(game);
//				gameDao.deleteGame(Integer.parseInt(ids[i]));
			} catch (Exception e) {
				e.printStackTrace();
//				删除失败，请重试;
				throw new GameDelErrorException(Constants.GAME_DELETE_ERROR);
			}
		}
	}
//
	
//	@Override
//	@Transactional
//	public void seleteGamesWhetherExistByGameType(String[] ids) throws Exception {
//		GameType gameType = new GameType();
//		Game game = new Game();
//		for (int i = 0; i < ids.length; i++) {
//			gameType.setId(Integer.parseInt(ids[i]));
//			gameType = gameTypeDao.selectOneGameType(gameType);
//			game.setGameType(gameType.getName());
//			List<Game> games =  gameDao.seleteGames(game);
//			if (games.size()>0) {
////				所选游戏类型中，有类型下仍有游戏，无法删除，请重新选择
//				throw new GameTypeException(Constant.GAME_TYPE_STILL_HAVE_GAME);
//			}
//		}
//	}
//
//
//	@Override
//	@Transactional
//	public void updateGameFollowingGameTypeUpdate(String gameTypeName, Integer status, String newGameTypeName) {
//		Game game = new Game();
//		game.setGameType(gameTypeName);
//		List<Game> games = gameDao.seleteGames(game);
//		for (int i = 0; i < games.size(); i++) {
//			game =  games.get(i);
//			game.setStatus(status);
//			game.setGameType(newGameTypeName);
//			gameDao.updateGame(game);
//		}
//	}
//
//	@Override
//	@Transactional
//	public Game selectOneGameType(Game game) {
//		return gameDao.selectOneGame(game);
//	}
//	
//	

}

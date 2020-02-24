package com.jc.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.jc.pojo.GameType;


public interface GameTypeService {
	/**
	 * 查找所有游戏
	 * @param name
	 * @param status
	 * @return
	 */
	public List<GameType> getGameTypes(GameType gameType);

	/**
	 *添加游戏
	 * @param gameType
	 * @param uploadFile
	 * @param imgPath
	 * @param fileName
	 * @throws Exception
	 */
	public void addGameType(GameType gameType,InputStream uploadFile,String imgPath, String fileName)throws Exception;
	/**
	 * 通过id查找游戏类型
	 * @param gameType
	 * @return
	 */
	public GameType getGameTypeById(GameType gameType);
	/**
	 * 修改游戏类型
	 * @param gameType
	 */
	public void modifyGameType(GameType gameType) throws Exception;
	/**
	 * 删除游戏类型
	 * @param delId
	 * @param serverPicturePath
	 */
	public void removeGameType(String[] ids) throws Exception;
//			throws GameTypeContainsGamesException;
//
//			throws UploadTypeException, GameTypeExistedException;
//	// 根据游戏数量排序，获取所有游戏类型
//	public List<GameType> getAllGameTypesByGameCount();
}

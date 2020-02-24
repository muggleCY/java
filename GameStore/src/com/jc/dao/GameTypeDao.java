package com.jc.dao;

import java.io.InputStream;
import java.util.List;

import com.jc.pojo.GameType;

public interface GameTypeDao {
	/**
	 * 查询所有游戏类型
	 * @param gameType
	 * @return
	 */
	public List<GameType> selectGameTypes(GameType gameType);
	/**
	 * 添加游戏类型
	 * @param gameType
	 */
	public void insertGameType(GameType gameType);
	/**
	 * 根据名字查询游戏类型
	 * @param name
	 * @return
	 * @throws GameTypeExistException
	 */
	public List<GameType> selectGameTypeByName(GameType gameType);
	/**
	 * 根据id查询游戏类型
	 * @param delId
	 * @return
	 */
	public GameType selectGameTypeById(GameType gameType);
	/**
	 * 修改游戏类型
	 * @param gameType
	 */
	public void updateGameType(GameType gameType);
	/**
	 * 删除游戏类型
	 * @param arrays
	 */
	public void deleteGameType(Integer id);

//	// 根据游戏数量排序，获取所有游戏类型
//	public List<GameType> selectAllGameTypesByGameCount();
//
//
//	// 根据id查游戏路径
//	// public List<String> selectPicturePathById(String[] delId);
}

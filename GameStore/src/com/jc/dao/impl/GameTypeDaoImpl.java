package com.jc.dao.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.jc.dao.GameTypeDao;
import com.jc.pojo.GameType;


@Repository
public class GameTypeDaoImpl implements GameTypeDao {
	private GameTypeDao gameTypeDao;
//	private GameDao gameDao;

	/**
	 * 查询所有游戏类型
	 */
	@Override
	public List<GameType> selectGameTypes(GameType gameType) {
		return gameTypeDao.selectGameTypes(gameType);
	}
	/**
	 * 添加游戏类型
	 */
	@Override
	public void insertGameType(GameType gameType) {
		gameTypeDao.insertGameType(gameType);
	}
	/**
	 * 根据名字查询游戏类型
	 */
	@Override
	public List<GameType> selectGameTypeByName(GameType gameType) {
		return gameTypeDao.selectGameTypeByName(gameType);
	}
	/**
	 * 根据id查询游戏类型
	 */
	@Override
	public GameType selectGameTypeById(GameType gameType) {
		return gameTypeDao.selectGameTypeById(gameType);
	}
	@Override
	public void updateGameType(GameType gameType) {
		gameTypeDao.updateGameType(gameType);
	}
	@Override
	public void deleteGameType(Integer id) {
		gameTypeDao.deleteGameType(id);
	}
//	// 根据游戏数量排序，获取所有游戏类型
//	public List<GameType> selectAllGameTypesByGameCount() {
//		return gameTypeDao.selectAllGameTypesByGameCount();
//	}
//
//	// 更新游戏类型的状态
//	public void updateGameTypeStatus(Map<String, Object> map) {
//		gameTypeDao.updateGameTypeStatus(map);
//	}
//
//	// 删除n个游戏类型
//	public void deleteGameType(String[] arrays) {
//		gameTypeDao.deleteGameType(arrays);
//	}
//
//	// 新建游戏类型
//	public void insertGameType(Map<String, Object> map) {
//		gameTypeDao.insertGameType(map);
//	}
//
//	// 根据名字查游戏类型
//	public GameType selectGameTypeByName(String name)
//			throws GameTypeExistedException {
//		return gameTypeDao.selectGameTypeByName(name);
//	}
//
//	// 根据id查游戏路径
//	// public List<String> selectPicturePathById(String[] delId){
//	// return gameTypeDao.selectPicturePathById(delId);
//	// }
//	// 根据id查game(用于查图片路径并删除，以及查询该类型名下是否存在游戏)
//	public List<GameType> selectGameByIds(String[] delId) {
//		return gameTypeDao.selectGameByIds(delId);
//	}

	public void setFactory(SqlSessionFactory factory) {
		this.gameTypeDao = factory.openSession().getMapper(GameTypeDao.class);
//		this.gameDao = factory.openSession().getMapper(GameDao.class);
	}



}

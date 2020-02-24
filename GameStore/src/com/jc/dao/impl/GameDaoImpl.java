package com.jc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.GameDao;
import com.jc.pojo.Game;
import com.jc.vo.GameVo;

@Repository
public class GameDaoImpl implements GameDao{
	private GameDao gameDao;
	/**
	 * 分页查所有游戏
	 */
	@Override
	public List<Game> selectGamesByPage(Map<String, Object> map) {
		return gameDao.selectGamesByPage(map);
	}
	@Override
	public List<GameVo> selectGames(Map<String, Object> map) {
		return gameDao.selectGames(map);
	}

	@Override
	public Integer countGame(Map<String, Object> map) {
		return gameDao.countGame(map);
	}
//	@Override
//	public List<GameVo> selectGamesForUser(Map<String, Object> map) {
//		return gameDao.selectGamesForUser(map);
//	}
//	
	@Override
	public Integer countGameForUser(Map<String, Object> map) {
		return gameDao.countGameForUser(map);
	}
	/**
	 * 查找一个游戏
	 */
	@Override
	public Game selectGame(Integer id) {
		return gameDao.selectGame(id);
	}
	/**
	 * 添加游戏
	 */
	@Override
	public void insertGame(Game game) {
		gameDao.insertGame(game);
	}
	/**
	 * 更新游戏
	 */
	@Override
	public void updateGame(Game game) {
		gameDao.updateGame(game);
	}
	/**
	 * 删除游戏
	 */
	@Override
	public void deleteGame(Integer id) {
		gameDao.deleteGame(id);
	}
	/**
	 * 通过名字查游戏
	 * @param game
	 * @return
	 */
	@Override
	public Game selectGameByName(Game game) {
		return gameDao.selectGameByName(game);
	}

	@Override
	public List<Game> seleteGamesByGameType(Integer gameType) {
		return gameDao.seleteGamesByGameType(gameType);
	}
	
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.gameDao = factory.openSession().getMapper(GameDao.class);
	}







	
}

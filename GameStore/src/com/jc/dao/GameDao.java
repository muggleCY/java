package com.jc.dao;

import java.util.List;
import java.util.Map;

import com.jc.pojo.Game;
import com.jc.vo.GameVo;

public interface GameDao {
	/**
	 * 分页查所有游戏
	 * @param map
	 * @return
	 */
	public List<Game> selectGamesByPage(Map<String, Object> map);
	public List<GameVo> selectGames(Map<String, Object> map);

	public Integer countGame(Map<String , Object> map);
//	public List<GameVo> selectGamesForUser(Map<String, Object> map);
//	
	public Integer countGameForUser(Map<String , Object> map);
	/**
	 * 查找一个游戏
	 * @param game
	 * @return
	 */
	public Game selectGame(Integer id);
	/**
	 * 添加游戏
	 * @param game
	 */
	public void insertGame(Game game);
	/**
	 * 修改游戏
	 * @param game
	 */
	public void updateGame(Game game);
	/**
	 * 删除游戏
	 * @param id
	 */
	public void deleteGame(Integer id);
	/**
	 * 通过名字查找游戏
	 * @param game
	 * @return
	 */
	public Game selectGameByName(Game game);

	
	public List<Game> seleteGamesByGameType(Integer gameType);
}

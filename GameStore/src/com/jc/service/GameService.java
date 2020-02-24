package com.jc.service;


import java.io.InputStream;
import java.util.List;

import com.jc.pojo.Game;
import com.jc.util.Pager;
import com.jc.vo.GameVo;


public interface GameService {
	/**
	 * 分页查所有游戏
	 * @param pageNo
	 * @param gameName
	 * @param gameType
	 * @return
	 */
	public Pager<Game> selectGamesByPage(Integer pageNo, String gameName, String gameType);
	public Pager<GameVo> selectGames(Integer pageNo, String gameName, String gameType);
	public Pager<GameVo> selectGamesForUser(Integer pageNo, String gameName, String gameType);
	/**
	 * 查找一个游戏
	 * @param id
	 * @return
	 */
	public Game selectGame(Integer id);
	/**
	 * 添加/修改
	 */
	public void addOrModifyGame(Game game,InputStream coverFile, InputStream screen1File,
			InputStream screen2File, InputStream screen3File,  String coverPath,String screenPath,
			String coverName,String screen1Name,String screen2Name,String screen3Name) throws Exception;
	/**
	 * 删除
	 */
	public void removeGame(String[] ids) throws Exception;
//	public Game selectOneGameType(Game game);
//	public void seleteGamesWhetherExistByGameType(String[] ids) throws Exception;
//	public void updateGameFollowingGameTypeUpdate(String gameTypeName, Integer status, String newGameTypeName);
}

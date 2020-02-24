package com.jc.pojo;

import java.util.Date;



public class Game {
	private Integer id;
	private String gameName;
	private Integer gameStatus;
	private String gameCover;
	private String gameScreen1;
	private String gameScreen2;
	private String gameScreen3;
	private Double gameCurrency;
	private Double gameTariffe;
	private String gameDevelopers;
	private String gameFiling;
	private String gameDetail;
	private String gameIntroduction;
	private Date createTime;
	private Date updateTime;
	private Integer gameType;

	public Game() {

	}

	public Game(Integer id, String gameName, Integer gameStatus,
			String gameCover, String gameScreen1, String gameScreen2,
			String gameScreen3, Double gameCurrency, Double gameTariffe,
			String gameDevelopers, String gameFiling, String gameDetail,
			String gameIntroduction, Date createTime, Date updateTime,
			Integer gameType) {
		super();
		this.id = id;
		this.gameName = gameName;
		this.gameStatus = gameStatus;
		this.gameCover = gameCover;
		this.gameScreen1 = gameScreen1;
		this.gameScreen2 = gameScreen2;
		this.gameScreen3 = gameScreen3;
		this.gameCurrency = gameCurrency;
		this.gameTariffe = gameTariffe;
		this.gameDevelopers = gameDevelopers;
		this.gameFiling = gameFiling;
		this.gameDetail = gameDetail;
		this.gameIntroduction = gameIntroduction;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.gameType = gameType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Integer getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(Integer gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getGameCover() {
		return gameCover;
	}

	public void setGameCover(String gameCover) {
		this.gameCover = gameCover;
	}

	public String getGameScreen1() {
		return gameScreen1;
	}

	public void setGameScreen1(String gameScreen1) {
		this.gameScreen1 = gameScreen1;
	}

	public String getGameScreen2() {
		return gameScreen2;
	}

	public void setGameScreen2(String gameScreen2) {
		this.gameScreen2 = gameScreen2;
	}

	public String getGameScreen3() {
		return gameScreen3;
	}

	public void setGameScreen3(String gameScreen3) {
		this.gameScreen3 = gameScreen3;
	}

	public Double getGameCurrency() {
		return gameCurrency;
	}

	public void setGameCurrency(Double gameCurrency) {
		this.gameCurrency = gameCurrency;
	}

	public Double getGameTariffe() {
		return gameTariffe;
	}

	public void setGameTariffe(Double gameTariffe) {
		this.gameTariffe = gameTariffe;
	}

	public String getGameDevelopers() {
		return gameDevelopers;
	}

	public void setGameDevelopers(String gameDevelopers) {
		this.gameDevelopers = gameDevelopers;
	}

	public String getGameFiling() {
		return gameFiling;
	}

	public void setGameFiling(String gameFiling) {
		this.gameFiling = gameFiling;
	}

	public String getGameDetail() {
		return gameDetail;
	}

	public void setGameDetail(String gameDetail) {
		this.gameDetail = gameDetail;
	}

	public String getGameIntroduction() {
		return gameIntroduction;
	}

	public void setGameIntroduction(String gameIntroduction) {
		this.gameIntroduction = gameIntroduction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

//	public Game(Integer id, String gameName, Integer gameStatus,
//			String gameCover, String gameScreen1, String gameScreen2,
//			String gameScreen3, Double gameCurrency, Double gameTariffe,
//			String gameDevelopers, String gameFiling, String gameDetail,
//			String gameIntroduction, Date createTime, Date updateTime,
//			GameType gameType) {
//		super();
//		this.id = id;
//		this.gameName = gameName;
//		this.gameStatus = gameStatus;
//		this.gameCover = gameCover;
//		this.gameScreen1 = gameScreen1;
//		this.gameScreen2 = gameScreen2;
//		this.gameScreen3 = gameScreen3;
//		this.gameCurrency = gameCurrency;
//		this.gameTariffe = gameTariffe;
//		this.gameDevelopers = gameDevelopers;
//		this.gameFiling = gameFiling;
//		this.gameDetail = gameDetail;
//		this.gameIntroduction = gameIntroduction;
//		this.createTime = createTime;
//		this.updateTime = updateTime;
//		this.gameType = gameType;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getGameName() {
//		return gameName;
//	}
//
//	public void setGameName(String gameName) {
//		this.gameName = gameName;
//	}
//
//	public Integer getGameStatus() {
//		return gameStatus;
//	}
//
//	public void setGameStatus(Integer gameStatus) {
//		this.gameStatus = gameStatus;
//	}
//
//	public String getGameCover() {
//		return gameCover;
//	}
//
//	public void setGameCover(String gameCover) {
//		this.gameCover = gameCover;
//	}
//
//	public String getGameScreen1() {
//		return gameScreen1;
//	}
//
//	public void setGameScreen1(String gameScreen1) {
//		this.gameScreen1 = gameScreen1;
//	}
//
//	public String getGameScreen2() {
//		return gameScreen2;
//	}
//
//	public void setGameScreen2(String gameScreen2) {
//		this.gameScreen2 = gameScreen2;
//	}
//
//	public String getGameScreen3() {
//		return gameScreen3;
//	}
//
//	public void setGameScreen3(String gameScreen3) {
//		this.gameScreen3 = gameScreen3;
//	}
//
//	public Double getGameCurrency() {
//		return gameCurrency;
//	}
//
//	public void setGameCurrency(Double gameCurrency) {
//		this.gameCurrency = gameCurrency;
//	}
//
//	public Double getGameTariffe() {
//		return gameTariffe;
//	}
//
//	public void setGameTariffe(Double gameTariffe) {
//		this.gameTariffe = gameTariffe;
//	}
//
//	public String getGameDevelopers() {
//		return gameDevelopers;
//	}
//
//	public void setGameDevelopers(String gameDevelopers) {
//		this.gameDevelopers = gameDevelopers;
//	}
//
//	public String getGameFiling() {
//		return gameFiling;
//	}
//
//	public void setGameFiling(String gameFiling) {
//		this.gameFiling = gameFiling;
//	}
//
//	public String getGameDetail() {
//		return gameDetail;
//	}
//
//	public void setGameDetail(String gameDetail) {
//		this.gameDetail = gameDetail;
//	}
//
//	public String getGameIntroduction() {
//		return gameIntroduction;
//	}
//
//	public void setGameIntroduction(String gameIntroduction) {
//		this.gameIntroduction = gameIntroduction;
//	}
//
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
//
//	public GameType getGameType() {
//		return gameType;
//	}
//
//	public void setGameType(GameType gameType) {
//		this.gameType = gameType;
//	}
//
//	

}

package com.jc.vo;

import java.util.Date;

public class ExpenseVo {
	private Integer userId;
	private Integer gameId;
	private String username;
	private String gameName;
	private Integer oprations;
	private Double monetary;
	private Date createTime;
	private Integer downloads;
	public ExpenseVo() {
		super();
	}


	public ExpenseVo(Integer userId, Integer gameId, String username,
			String gameName, Integer oprations, Double monetary,
			Date createTime, Integer downloads) {
		super();
		this.userId = userId;
		this.gameId = gameId;
		this.username = username;
		this.gameName = gameName;
		this.oprations = oprations;
		this.monetary = monetary;
		this.createTime = createTime;
		this.downloads = downloads;
	}


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Integer getOprations() {
		return oprations;
	}
	public void setOprations(Integer oprations) {
		this.oprations = oprations;
	}
	public Double getMonetary() {
		return monetary;
	}
	public void setMonetary(Double monetary) {
		this.monetary = monetary;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	
}

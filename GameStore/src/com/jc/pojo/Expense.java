package com.jc.pojo;

import java.util.Date;

public class Expense {
	private Integer id;
	private Integer userId;
	private Integer gameId;
	private Double monetary;
	private Integer oprations;
	private Date createTime;
	private Integer downloads;
	public Expense() {
		super();
	}
	
	public Expense(Integer id, Integer userId, Integer gameId, Double monetary,
			Integer oprations, Date createTime, Integer downloads) {
		super();
		this.id = id;
		this.userId = userId;
		this.gameId = gameId;
		this.monetary = monetary;
		this.oprations = oprations;
		this.createTime = createTime;
		this.downloads = downloads;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getMonetary() {
		return monetary;
	}
	public void setMonetary(Double monetary) {
		this.monetary = monetary;
	}
	public Integer getOperations() {
		return oprations;
	}
	public void setOperations(Integer operations) {
		this.oprations = operations;
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

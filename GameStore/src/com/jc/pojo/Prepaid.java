package com.jc.pojo;

import java.util.Date;

public class Prepaid {
	private Integer id;
	private Integer userId;
	private Integer cardId;
	private Date createTime;
	public Prepaid() {
		super();
	}
	public Prepaid(Integer id, Integer userId, Integer cardId, Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.cardId = cardId;
		this.createTime = createTime;
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
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

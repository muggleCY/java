package com.jc.pojo;

import java.util.Date;

public class Exchange {
	private Integer id;
	private Integer provId;
	private Double charge;
	private Date createTime;
	private Date updateTime;
	private Integer exchangeStatus;
	public Exchange() {
		super();
	}
	public Exchange(Integer id, Integer provId, Double charge, Date createTime,
			Date updateTime, Integer exchangeStatus) {
		super();
		this.id = id;
		this.provId = provId;
		this.charge = charge;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.exchangeStatus = exchangeStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProvId() {
		return provId;
	}
	public void setProvId(Integer provId) {
		this.provId = provId;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
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
	public Integer getExchangeStatus() {
		return exchangeStatus;
	}
	public void setExchangeStatus(Integer exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

}

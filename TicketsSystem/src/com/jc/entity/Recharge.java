package com.jc.entity;

public class Recharge {
	private Integer rgid;
	private Integer uid;
	private Double rechargeMoney;
	private String applyTime;
	private Integer rechargeMode;
	
	public Recharge() {

	}
	public Recharge(Integer rgid, Integer uid, Double rechargeMoney, String applyTime, Integer rechargeMode) {
		this.rgid = rgid;
		this.uid = uid;
		this.rechargeMoney = rechargeMoney;
		this.applyTime = applyTime;
		this.rechargeMode = rechargeMode;
	}
	
	public Integer getRgid() {
		return rgid;
	}
	public void setRgid(Integer rgid) {
		this.rgid = rgid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Double getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getRechargeMode() {
		return rechargeMode;
	}
	public void setRechargeMode(Integer rechargeMode) {
		this.rechargeMode = rechargeMode;
	}
	
}

package com.jc.entity;

public class RecordAndTicket {
	private Integer rdid;
	private Integer tid;
	private String startStation;
	private String arriveStation;
	private String buyTime;
	private Integer recordMode;
	
	
	
	public RecordAndTicket() {
	}

	public RecordAndTicket(Integer rdid, Integer tid, String startStation, String arriveStation, String buyTime, Integer recordMode) {
		this.rdid = rdid;
		this.tid = tid;
		this.startStation = startStation;
		this.arriveStation = arriveStation;
		this.buyTime = buyTime;
		this.recordMode = recordMode;
	}

	public Integer getRdid() {
		return rdid;
	}
	public void setRdid(Integer rdid) {
		this.rdid = rdid;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getArriveStation() {
		return arriveStation;
	}
	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public Integer getRecordMode() {
		return recordMode;
	}
	public void setRecordMode(Integer recordMode) {
		this.recordMode = recordMode;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return tid;
	}
}

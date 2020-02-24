package com.jc.entity;
/**
 * 购买记录类
 * @author soft01
 *
 */
public class Record {
	private Integer rdid;
	private Integer uid;
	private Integer tid;
	private String buyTime;
	private Integer recordMode;
	
	public Record() {
		
	}
	
	public Record(Integer rdid, Integer uid, Integer tid, String buyTime, Integer recordMode) {
		this.rdid = rdid;
		this.uid = uid;
		this.tid = tid;
		this.buyTime = buyTime;
		this.recordMode = recordMode;
	}

	
	
	public Integer getRdid() {
		return rdid;
	}

	public void setRdid(Integer rdid) {
		this.rdid = rdid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
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

}

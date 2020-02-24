package com.jc.entity;
/**
 * 车票类
 * @author soft01
 *
 */
public class Ticket {
	private Integer tid;
	private String startStation;
	private String arriveStation;
	private String startTime;
	private Integer ticketNum;
	private Integer ticketMode;
	private Double ticketMoney;
	
	public Ticket() {
		
	}

	public Ticket(Integer tid, String startStation, String arriveStation, String startTime, Integer ticketNum, Integer ticketMode, Double ticketMoney) {
		this.tid = tid;
		this.startStation = startStation;
		this.arriveStation = arriveStation;
		this.startTime = startTime;
		this.ticketNum = ticketNum;
		this.ticketMode = ticketMode;
		this.ticketMoney = ticketMoney;
	}

	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}
	public Integer getTicketMode() {
		return ticketMode;
	}
	public void setTicketMode(Integer ticketMode) {
		this.ticketMode = ticketMode;
	}

	public void setTicketMoney(Double ticketMoney) {
		this.ticketMoney = ticketMoney;
	}

	public Double getTicketMoney() {
		return ticketMoney;
	}
}

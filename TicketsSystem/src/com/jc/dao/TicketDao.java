package com.jc.dao;

import java.sql.ResultSet;
import java.util.List;

import com.jc.entity.Ticket;

public interface TicketDao {
//	//通过站点和出发时间查找车票
	public Ticket selectTicketByStationOrTime(String startStation,
			String arriveStation,String startTime);
	public List<Ticket> selectTicketByPageAndCond(String startStation,
			String arriveStation,String startTime,Integer pageNum, Integer pageSize);
	public Ticket selectTicketById(Integer tid);
	public void updateTicketById(Ticket ticket);
	public void updateTicketForNum(Ticket ticket);
	public void insertTicket(Ticket ticket);
}

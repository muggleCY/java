package com.jc.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.jc.entity.Record;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.*;
import com.jc.util.Pager;

public interface TicketService {
//	public List<Ticket> queryTicketByStationOrTime(String startStation,
//			String arriveStation, String startTime) throws Exception;
////	public Map<String, Object> queryAllStudentForTable();
//	//查询起始站
//	public List<String> selectStartStation(String startStation,String arriveStation) throws Exception;
//	//查询终点站
//	public List<String> selectArriveStation(String startStation,String arriveStation)  throws Exception;
//	
////	public List<String> selectStation(String startStation,String arriveStation);
	public Pager<Ticket> getTicketByPageAndCond(String startStation,
			String arriveStation, String startTime, Integer pageNum) throws Exception;
	public Ticket getTicketById(Integer tid) throws NotBuyTicketException,Exception;
	public void updateTicketById(Ticket ticket) throws Exception;
	public void updateTicketForNum(Ticket ticket) throws NoTicketNumException, Exception;
	public void insertTicket(String startStation, String arriveStation, String startTime,Integer ticketNum,Double ticketMoney) throws TicketIsExistException,Exception;
	public void buy(Integer tid, Integer uid) throws NoTicketNumException, MoneyNotEnoughException, AlreadyBuyException, NotBuyTicketException,TicketExpireException, Exception;
	public void refundTicket(Integer rid) throws AlreadyRefundException,Exception;
//	public void changeMode(Integer tid);
}

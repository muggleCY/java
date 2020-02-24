package com.jc.service.proxy;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jc.entity.Record;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.*;
import com.jc.factory.ObjectFactory;
import com.jc.service.TicketService;
import com.jc.trans.Transaction;
import com.jc.util.Pager;

public class TicketServiceProxy implements TicketService{
	TicketService ticketService = (TicketService) ObjectFactory.getObject("ticketService");
	Transaction trans = (Transaction) ObjectFactory.getObject("trans");
	
	@Override
	public Pager<Ticket> getTicketByPageAndCond(String startStation, String arriveStation, String startTime, Integer pageNum) throws Exception {
		Pager<Ticket> page = null;
		trans.begin();
		try {
			page = ticketService.getTicketByPageAndCond(startStation, arriveStation, startTime,pageNum);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		return page;
	}

	@Override
	public Ticket getTicketById(Integer tid) throws Exception {
		Ticket ticket = null;
		trans.begin();
		try {
			ticket = ticketService.getTicketById(tid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		
		return ticket;
	}

	@Override
	public void updateTicketById(Ticket ticket) throws Exception {
		trans.begin();
		try {
			ticketService.updateTicketById(ticket);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}
	@Override
	public void updateTicketForNum(Ticket ticket) throws NoTicketNumException,Exception {
		trans.begin();
		try {
			ticketService.updateTicketForNum(ticket);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void insertTicket(String startStation, String arriveStation, String startTime,Integer ticketNum,Double ticketMoney) throws TicketIsExistException,Exception {
		trans.begin();
		try {
			ticketService.insertTicket(startStation,arriveStation,startTime,ticketNum,ticketMoney);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void buy(Integer tid, Integer uid) throws NoTicketNumException, MoneyNotEnoughException, AlreadyBuyException, Exception {
		trans.begin();
		try {
			ticketService.buy(tid,uid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void refundTicket(Integer rid) throws AlreadyRefundException,Exception {
		trans.begin();
		try {
			ticketService.refundTicket(rid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}


//	@Override
//	public List<Ticket> queryTicketByStationOrTime(String startStation, 
//			String arriveStation, String startTime) throws Exception {
//		List<Ticket> ticketList = null;
//		trans.begin();
//		try {
//			ticketList = ticketService.queryTicketByStationOrTime(startStation, arriveStation, startTime);
////			System.out.println("111"+ticketList);
//			trans.commit();
//		} catch (Exception e) {
//			trans.rollback();
//			throw e;
//		}
//		
//		return ticketList;
//	}
//
//	@Override
//	public List<String> selectArriveStation(String startStation, String arriveStation) throws Exception {
//		List<String> arriveStationList = new ArrayList<String>();
//		trans.begin();
//		try {
//			arriveStationList = ticketService.selectArriveStation(startStation, arriveStation);
//			trans.commit();
//		} catch (Exception e) {
//			trans.rollback();
//			throw e;
//		}
//		return arriveStationList;
//	}
//
//	@Override
//	public List<String> selectStartStation(String startStation, String arriveStation) throws Exception {
//		List<String> startStationList = new ArrayList<String>();
//		trans.begin();
//		try {
//			startStationList = ticketService.selectStartStation(startStation, arriveStation);
//			trans.commit();
//		} catch (Exception e) {
//			trans.rollback();
//			throw e;
//		}
//		return startStationList;
//	}


//	@Override
//	public Map<String, Object> queryAllStudentForTable() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

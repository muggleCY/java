package com.jc.service.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jc.constant.Constants;
import com.jc.dao.*;
import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.entity.Ticket;
import com.jc.entity.User;
import com.jc.exception.*;
import com.jc.factory.ObjectFactory;
import com.jc.service.TicketService;
import com.jc.util.Pager;

public class TicketServiceImpl implements TicketService{
	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");
	TicketDao ticketDao = (TicketDao) ObjectFactory.getObject("ticketDao");
	RecordDao recordDao = (RecordDao) ObjectFactory.getObject("recordDao");
	RecordAndTicketDao recordAndTicketDao = (RecordAndTicketDao) ObjectFactory.getObject("recordAndTicketDao");

	CountDao countDao = (CountDao) ObjectFactory.getObject("countDao");
	//查询分页
	@Override
	public Pager<Ticket> getTicketByPageAndCond(String startStation, String arriveStation, String startTime, Integer pageNum) {
		Pager<Ticket> page = new Pager<Ticket>();
	    page.setPageNum(pageNum);
	    List<Ticket> list = ticketDao.selectTicketByPageAndCond(startStation, arriveStation, startTime, pageNum, Constants.PAGE_SIZE_3);
		page.setData(list);
		Integer totalCount = countDao.countTicket(startStation, arriveStation, startTime);
		System.out.println("totalCount"+totalCount);
		page.setTotalPage(totalCount,Constants.PAGE_SIZE_3);
		return page;
	}
	//查询某个车票
	@Override
	public Ticket getTicketById(Integer tid) throws  TicketNumNotEnoughException {
		Ticket ticket = ticketDao.selectTicketById(tid);

		if(ticket.getTicketNum() <= 0 ){
			throw new TicketNumNotEnoughException("票数不足");
		}
		return ticket;
	}
	//更新车票
	@Override
	public void updateTicketById(Ticket ticket) {
		ticketDao.updateTicketById(ticket);
	}
	@Override
	public void updateTicketForNum(Ticket ticket) throws NoTicketNumException {
		if(ticket.getTicketNum() == 0){
			throw new NoTicketNumException("没有票了");
		}
		ticket.setTicketNum(ticket.getTicketNum()-1);
		ticketDao.updateTicketForNum(ticket);
	}
	//添加车票
	@Override
	public void insertTicket(String startStation, String arriveStation, String startTime,Integer ticketNum,Double ticketMoney) throws TicketIsExistException {
		if(ticketDao.selectTicketByStationOrTime(startStation,
				arriveStation, startTime)!=null){
			throw new TicketIsExistException("该车票已经存在");
		}
		Ticket ticket = new Ticket();
		ticket.setStartStation(startStation);
		ticket.setArriveStation(arriveStation);
		ticket.setStartTime(startTime);
		ticket.setTicketNum(ticketNum);
		ticket.setTicketMoney(ticketMoney);
		ticketDao.insertTicket(ticket);
	}
	//购买
	@Override
	public void buy(Integer tid, Integer uid) throws NoTicketNumException, MoneyNotEnoughException, AlreadyBuyException, NotBuyTicketException, TicketExpireException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Ticket ticket = ticketDao.selectTicketById(tid);
		User user = userDao.selectUserById(uid);
		try {
			if(format.parse(ticket.getStartTime()).before(date)){
				throw new TicketExpireException("车票已过期");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		if(ticket.getTicketNum() <= 0){
			throw new NoTicketNumException("车票不足");
		}
		if (ticket.getTicketMode() == 1){
			throw new	NotBuyTicketException("不可购买");
		}
		if (user.getUserMoney() < ticket.getTicketMoney()){
			throw new MoneyNotEnoughException("钱不够了");
		}

		List<RecordAndTicket> recordList = recordAndTicketDao.selectRecord(user.getUid());

		Integer error = -1;
		for (int i = 0; i < recordList.size(); i++) {
			if(ticket.getTid() == recordList.get(i).getTid()){
				error = recordList.get(i).getRecordMode();
			}
		}
		System.out.println(error+"error");
		if ( error != 1 &&error!=-1){
			throw new AlreadyBuyException("已经购买");
		}
		ticket.setTicketNum(ticket.getTicketNum()-1);
		System.out.println("ticketNum"+ticket.getTicketNum());
		ticketDao.updateTicketForNum(ticket);
		user.setUserMoney(user.getUserMoney()-ticket.getTicketMoney());
		System.out.println("userMoney"+user.getUserMoney());
		userDao.updatemoney(user);
		Record record = new Record();
		record.setUid(user.getUid());
		record.setTid(ticket.getTid());
		record.setRecordMode(0);
		record.setBuyTime(format.format(date));
//		if(error == 1){
//			recordDao.updateRecord(record);
//		}else{
			recordDao.insertRecord(record);
//		}
		recordAndTicketDao.selectRecord(user.getUid());
	}
	//退票
	@Override
	public void refundTicket(Integer rid) throws AlreadyRefundException {
		Record record = recordDao.selectOneRecord(rid);
		if ( record.getRecordMode() == 1){
			throw new AlreadyRefundException("已经退票");
		}
		record.setRecordMode(1);
		recordDao.updateRecord(record);
		recordAndTicketDao.selectRecord(record.getUid());
		Ticket ticket = ticketDao.selectTicketById(record.getTid());
		ticket.setTicketNum(ticket.getTicketNum() + 1);
		ticketDao.updateTicketForNum(ticket);
		User user = userDao.selectUserById(record.getUid());
		user.setUserMoney(user.getUserMoney() + ticket.getTicketMoney());
		System.out.println("tuipiao"+user.getUserMoney());
		userDao.updatemoney(user);
	}

//	@Override
//	public void changeMode(Integer tid) {
//	Ticket ticket = ticketDao.selectTicketById(tid);
//
//	}

//	@Override
//	public List<Ticket> queryTicketByStationOrTime(String startStation, 
//			String arriveStation, String startTime) {	
//		return ticketDao.selectTicketByStationOrTime(startStation,
//				arriveStation, startTime);
//	}
////	@Override
////	public Map<String, Object> queryAllStudentForTable() {
////		
////		
////		return null;
////	}
//
//	@Override
//	public List<String> selectArriveStation(String startStation, String arriveStation) {
//		List<String> arriveStationList = new ArrayList<String>();
//		List<Ticket> result = ticketDao.selectTicketByStationOrTime(startStation, arriveStation, null);
//		for (int i = 0; i < result.size(); i++) {
//			arriveStationList.add(result.get(i).getArriveStation());
//		}
//		return arriveStationList;
//	}
//
//	@Override
//	public List<String> selectStartStation(String startStation, String arriveStation) {
//		List<String> startStationList = new ArrayList<String>();
//		List<Ticket> result = ticketDao.selectTicketByStationOrTime(startStation, arriveStation, null);
//		for (int i = 0; i < result.size(); i++) {
//			startStationList.add(result.get(i).getStartStation());
//		}
//		return startStationList;
//	}
//
//
////	@Override
////	public List<String> selectStation(String startStation, String arriveStation) {
////		List<String> stationList = new ArrayList<String>();
////		List<Ticket> result = ticketDao.selectTicketByStationOrTime(startStation, arriveStation, null);
////		Class ticketClass = Ticket.class;
////		Field[] fields = ticketClass.getDeclaredFields();
////		
////		return null;
////	}


}

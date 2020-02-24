package com.jc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.jc.dao.TicketDao;
import com.jc.entity.Ticket;
import com.jc.mapper.TicketMapper;
import com.jc.util.JDBCTemplate;

public class TicketDaoImpl implements TicketDao{
	JDBCTemplate<Ticket> temp = new JDBCTemplate<Ticket>();

	@Override
	public List<Ticket> selectTicketByPageAndCond(String startStation, String arriveStation, String startTime, Integer pageNum, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer()
				.append(" select ")
				.append(" 	id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney ")
				.append(" from ")
				.append(" 	t_ticket ")
				.append(" where ")
//				.append(" 	1 = 1 ");
				.append(" 	ticket_mode!=2 ");
		if(startStation!=null&&!startStation.equals("")){
			sql.append(" and startStation like ? ");
			params.add(startStation+ "%");
		}
		if(arriveStation!=null&&!arriveStation.equals("")){
			sql.append(" and arriveStation like ? ");
			params.add(arriveStation+ "%");
		}
		if(startTime!=null&&!startTime.equals("")){
			sql.append(" and startTime like ? ");
			params.add(startTime + "%");
		}
		sql.append(" limit ")
				.append(" 	?,? ")
				.toString();
		params.add((pageNum-1)*pageSize);
		params.add(pageSize);
		return temp.selectAll(new TicketMapper(), sql.toString(), params.toArray());
	}

	@Override
	public Ticket selectTicketById(Integer tid) {
		String sql = new StringBuffer()
			.append(" select ")
			.append(" 	id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney ")
			.append(" from ")
			.append(" 	t_ticket ")
			.append(" where ")
			.append(" 	id = ? ")
			.toString();
		return temp.selectOne(new TicketMapper(), sql, tid);
	}

	@Override
	public void updateTicketById(Ticket ticket) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_ticket ")
		.append(" set ")
		.append(" 	ticket_mode =? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql, ticket.getTicketMode(),ticket.getTid());
	}
	@Override
	public void updateTicketForNum(Ticket ticket) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_ticket ")
		.append(" set ")
		.append(" 	ticket_Num = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql, ticket.getTicketNum(),ticket.getTid());
	}

	@Override
	public void insertTicket(Ticket ticket) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_ticket ")
		.append(" values")
		.append(" 	(null,?,?,?,?,0,?) ")
		.toString(); 
		temp.insert(sql, ticket.getStartStation(),ticket.getArriveStation(),ticket.getStartTime(),
				ticket.getTicketNum(),ticket.getTicketMoney());
	}


	
	@Override
	public Ticket selectTicketByStationOrTime(String startStation,
			String arriveStation, String startTime) {
		StringBuffer sql = new StringBuffer()
			.append(" select ")
			.append(" 	id,startStation,arriveStation,startTime,ticket_Num,ticket_mode,ticketmoney ")
			.append(" from ")
			.append(" 	t_ticket ")
			.append(" where ")
			.append(" 	startStation = ? ")
			.append(" and arriveStation = ? ")
			.append(" and startTime = ? ");

		return temp.selectOne(new TicketMapper(), sql.toString(), startStation,arriveStation,startTime);
		
	}



}

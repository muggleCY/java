package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapperObject(ResultSet rs) throws Exception {
		Ticket ticket = new Ticket();
		ticket.setTid(rs.getInt("id"));
		ticket.setStartStation(rs.getString("startStation"));
		ticket.setArriveStation(rs.getString("arriveStation"));
		ticket.setStartTime(rs.getString("startTime"));
		ticket.setTicketNum(rs.getInt("ticket_Num"));
		ticket.setTicketMode(rs.getInt("ticket_mode"));
		ticket.setTicketMoney(rs.getDouble("ticketmoney"));
		return ticket;
	}

}

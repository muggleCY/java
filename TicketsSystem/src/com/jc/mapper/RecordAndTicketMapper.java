package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.RecordAndTicket;

public class RecordAndTicketMapper implements RowMapper<RecordAndTicket>{

	@Override
	public RecordAndTicket mapperObject(ResultSet rs) throws Exception {
		RecordAndTicket record = new RecordAndTicket();
		record.setRdid(rs.getInt("id"));
//		record.setUid(rs.getInt("user_id"));
//		record.setTid(rs.getInt("ticket_id"));
		record.setTid(rs.getInt("ticket_id"));
		record.setStartStation(rs.getString("startStation"));
		record.setArriveStation(rs.getString("arriveStation"));
		record.setBuyTime(rs.getString("buyTime"));
		record.setRecordMode(rs.getInt("record_mode"));
		return record;
	}
	
	
}

package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.Record;
import com.jc.entity.Ticket;

public class RecordMapper implements RowMapper<Record>{

	@Override
	public Record mapperObject(ResultSet rs) throws Exception {
		Record record = new Record();
		record.setRdid(rs.getInt("id"));
		record.setUid(rs.getInt("user_id"));
		record.setTid(rs.getInt("ticket_id"));
		
		
		
		
		record.setBuyTime(rs.getString("buyTime"));
		record.setRecordMode(rs.getInt("record_mode"));
		return record;
	}
	
}

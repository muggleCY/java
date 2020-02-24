package com.jc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.jc.dao.RecordDao;
import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.entity.Ticket;
import com.jc.mapper.RecordAndTicketMapper;
import com.jc.mapper.RecordMapper;
import com.jc.mapper.TicketMapper;
import com.jc.util.JDBCTemplate;

public class RecordDaoImpl implements RecordDao{
	JDBCTemplate<Record> temp = new JDBCTemplate<Record>();

	@Override
	public void insertRecord(Record record) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_record ")
		.append(" values")
		.append(" 	(null,?,?,?,0) ")
		.toString(); 
		temp.insert(sql, record.getUid(),record.getTid(),record.getBuyTime());
	}

	@Override
	public void updateRecord(Record record) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_record ")
		.append(" set ")
		.append(" 	record_mode = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql, record.getRecordMode(),record.getRdid());		
	}

	@Override
	public Record selectOneRecord(Integer rdid) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,user_id, ticket_id, buyTime,record_mode ")
		.append(" from ")
		.append(" 	t_record ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	return temp.selectOne(new RecordMapper(), sql, rdid);
	}

}

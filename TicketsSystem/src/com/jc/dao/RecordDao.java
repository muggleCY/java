package com.jc.dao;

import java.util.List;

import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;

public interface RecordDao {
	public Record selectOneRecord(Integer rdid);
	public void insertRecord(Record record);
	public void updateRecord(Record record);
}

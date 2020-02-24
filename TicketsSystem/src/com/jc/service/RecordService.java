package com.jc.service;

import java.util.List;

import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.exception.AlreadyBuyException;
import com.jc.exception.AlreadyRefundException;

public interface RecordService {
	public List<RecordAndTicket> selectRecord(Integer uid) throws Exception;
	public void insertRecord(Record record) throws AlreadyBuyException,Exception;
	public void updateRecord(Record record) throws AlreadyRefundException,Exception;
	public Record selectOneRecord(Integer rdid) throws Exception;
}

package com.jc.service.proxy;

import java.util.List;

import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.exception.AlreadyRefundException;
import com.jc.factory.ObjectFactory;
import com.jc.service.RecordService;
import com.jc.trans.Transaction;

public class RecordServiceProxy implements RecordService{
	Transaction trans = (Transaction) ObjectFactory.getObject("trans");
	RecordService recordService = (RecordService) ObjectFactory.getObject("recordService");
	@Override
	public List<RecordAndTicket> selectRecord(Integer uid) throws Exception {
		List<RecordAndTicket> recordList = null;
		trans.begin();
		try {
			recordList = recordService.selectRecord(uid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		return recordList;
	}
	@Override
	public void insertRecord(Record record) throws Exception {
		trans.begin();
		try {
			recordService.insertRecord(record);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}
	@Override
	public void updateRecord(Record record) throws AlreadyRefundException,Exception {
		trans.begin();
		try {
			recordService.updateRecord(record);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}
	@Override
	public Record selectOneRecord(Integer rdid) throws Exception{
		Record record = null;
		trans.begin();
		try {
			record = recordService.selectOneRecord(rdid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		return record;
	}

}

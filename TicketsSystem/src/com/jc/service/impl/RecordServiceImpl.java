package com.jc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jc.dao.RecordAndTicketDao;
import com.jc.dao.RecordDao;
import com.jc.entity.Record;
import com.jc.entity.RecordAndTicket;
import com.jc.exception.AlreadyBuyException;
import com.jc.exception.AlreadyRefundException;
import com.jc.factory.ObjectFactory;
import com.jc.service.RecordService;

public class RecordServiceImpl implements RecordService{
	RecordDao recordDao = (RecordDao) ObjectFactory.getObject("recordDao");
	RecordAndTicketDao recordAndTicketDao = (RecordAndTicketDao) ObjectFactory.getObject("recordAndTicketDao");
	@Override
	public List<RecordAndTicket> selectRecord(Integer uid) {
		List<RecordAndTicket> recordList = new ArrayList<RecordAndTicket>();
		recordList = recordAndTicketDao.selectRecord(uid);
		return recordList;
	}
	@Override
	public void insertRecord(Record record) throws AlreadyBuyException {
		List<RecordAndTicket> recordList = recordAndTicketDao.selectRecord(record.getUid());
		Integer error = 0;
		for (int i = 0; i < recordList.size(); i++) {
			if(record.getTid() == recordList.get(i).getTid()){
				error++;
			}
		}
		if(error == 0||record.getRecordMode() == 1){
			recordDao.insertRecord(record);			
		}else{
			throw new AlreadyBuyException("已经购买");
		}
	}
	@Override
	public void updateRecord(Record record) throws AlreadyRefundException {
		if(record.getRecordMode() == 1){
			throw new AlreadyRefundException("已经退票");
		}else{
			record.setRecordMode(1);
			recordDao.updateRecord(record);
		}
	}
	@Override
	public Record selectOneRecord(Integer rdid) {
		Record record = recordDao.selectOneRecord(rdid);
		return record;
	}

}

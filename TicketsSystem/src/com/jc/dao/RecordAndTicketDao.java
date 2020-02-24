package com.jc.dao;

import com.jc.entity.RecordAndTicket;

import java.util.List;

public interface RecordAndTicketDao {
    public List<RecordAndTicket> selectRecord(Integer uId);
//    public void insertRecord(RecordAndTicket recordAndTicket);
//    public void updateRecord(RecordAndTicket recordAndTicket);
}

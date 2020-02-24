package com.jc.dao.impl;

import com.jc.dao.RecordAndTicketDao;
import com.jc.entity.RecordAndTicket;
import com.jc.mapper.RecordAndTicketMapper;
import com.jc.util.JDBCTemplate;

import java.util.List;

public class RecordAndTicketDaoImpl implements RecordAndTicketDao {
    JDBCTemplate<RecordAndTicket> temp = new JDBCTemplate<RecordAndTicket>();
    @Override
    public List<RecordAndTicket> selectRecord(Integer uid) {
//		String sql = new StringBuffer()
//			.append(" select ")
//			.append(" 	id,user_id, ticket_id, buyTime,record_mode ")
//			.append(" from ")
//			.append(" 	t_record ")
//			.append(" where ")
//			.append(" 	user_id = ? ")
//			.toString();
//		System.out.println(sql);
//		return temp.selectAll(new RecordMapper(), sql, String.valueOf(uid));
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	r.id,r.user_id,r.ticket_id, temp.startStation,temp.arriveStation, r.buyTime,r.record_mode ")
                .append(" from ")
                .append(" 	(select id,startStation,arriveStation from t_ticket) as temp,t_record as r ")
                .append(" where ")
                .append(" 	r.ticket_id = temp.id ")
                .append(" and user_id = ? ")
                .toString();
        return temp.selectAll(new RecordAndTicketMapper(), sql, String.valueOf(uid));
    }

//    @Override
//    public void insertRecord(RecordAndTicket recordAndTicket) {
//        String sql = new StringBuffer()
//                .append(" insert into ")
//                .append(" 	r.id,r.user_id,r.ticket_id, temp.startStation,temp.arriveStation, r.buyTime,r.record_mode ")
//                .append(" from ")
//                .append(" 	(select id,startStation,arriveStation from t_ticket) as temp,t_record as r ")
//                .append(" where ")
//                .append(" 	r.ticket_id = temp.id ")
//                .append(" and user_id = ? ")
//                .toString();
//    }
//
//    @Override
//    public void updateRecord(RecordAndTicket recordAndTicket) {
//
//    }
}

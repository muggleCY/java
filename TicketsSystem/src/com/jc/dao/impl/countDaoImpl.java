package com.jc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.jc.dao.CountDao;
import com.jc.entity.Ticket;
import com.jc.mapper.CountMapper;
import com.jc.util.JDBCTemplate;

public class countDaoImpl implements CountDao{
	JDBCTemplate<Integer> temp = new JDBCTemplate<Integer>();
	@Override
	public Integer countTicket(String startStation, String arriveStation, String startTime) {
		List<Object> params = new ArrayList<Object>(); 
		StringBuffer sql = new StringBuffer()
         .append(" select ")
         .append(" 	count(id) num ")
         .append(" from ")
         .append(" 	t_ticket ")
		 .append(" where ")
		 .append(" 	ticket_mode != 2 ");
		if(startStation!=null&&!startStation.equals("")){
			sql.append(" and startStation like ? ");
			params.add(startStation+"%");
		}
		if(arriveStation!=null&&!arriveStation.equals("")){
			sql.append(" and arriveStation like ? ");
			params.add(arriveStation+"%");
		}
		if(startTime!=null&&!startTime.equals("")){
			sql.append(" and startTime like ? ");
			params.add(startTime+"%");
		}
		System.out.println("aaaa"+temp.selectOne(new CountMapper(), sql.toString(), params.toArray()));
		 return temp.selectOne(new CountMapper(), sql.toString(), params.toArray());
	}

}

package com.jc.dao.impl;

import java.util.List;

import com.jc.dao.RechargeDao;
import com.jc.entity.Recharge;
import com.jc.entity.Record;
import com.jc.mapper.RechargeMapper;
import com.jc.mapper.RecordAndTicketMapper;
import com.jc.util.JDBCTemplate;

public class RechargeDaoImpl implements RechargeDao{
	JDBCTemplate<Recharge> temp = new JDBCTemplate<Recharge>();
	@Override
	public List<Recharge> selectRecharge() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,user_id,recharge_money,applyTime,recharge_mode ")
		.append(" from ")
		.append(" 	t_recharge ")
		.toString();
		return temp.selectAll(new RechargeMapper(), sql);
	}
	@Override
	public void insertRecharge(Recharge recharge) {
		String sql = new StringBuffer()
		.append(" insert into ")
		.append(" 	t_recharge ")
		.append(" values")
		.append(" 	(null,?,?,?,1) ")
		.toString(); 
		temp.insert(sql, recharge.getUid(),recharge.getRechargeMoney(),recharge.getApplyTime());
	}
	@Override
	public Recharge selectOne(Integer rgid) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,user_id,recharge_money,applyTime,recharge_mode ")
		.append(" from ")
		.append(" 	t_recharge ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		return temp.selectOne(new RechargeMapper(), sql, rgid);
	}
	@Override
	public void updateRecharge(Recharge recharge) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_recharge ")
		.append(" set ")
		.append(" 	recharge_mode = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql, recharge.getRechargeMode(),recharge.getRgid());		
	}
	
}

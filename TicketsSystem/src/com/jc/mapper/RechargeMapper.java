package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.Recharge;

public class RechargeMapper implements RowMapper<Recharge>{

	@Override
	public Recharge mapperObject(ResultSet rs) throws Exception {
		Recharge recharge = new Recharge();
		recharge.setRgid(rs.getInt("id"));
		recharge.setUid(rs.getInt("user_id"));
		recharge.setRechargeMoney(rs.getDouble("recharge_money"));
		recharge.setApplyTime(rs.getString("applyTime"));
		recharge.setRechargeMode(rs.getInt("recharge_mode"));
		return recharge;
	}
	
}

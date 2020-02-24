package com.jc.dao;

import java.util.List;

import com.jc.entity.Recharge;

public interface RechargeDao {
	public List<Recharge> selectRecharge();
	public Recharge selectOne(Integer rgid);
	public void insertRecharge(Recharge recharge);
	public void updateRecharge(Recharge recharge);
}

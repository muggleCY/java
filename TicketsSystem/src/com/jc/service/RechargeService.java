package com.jc.service;

import java.util.List;

import com.jc.entity.Recharge;
import com.jc.entity.User;
import com.jc.exception.AlreadyPassException;

public interface RechargeService {
	public List<Recharge> selectRecharge() throws Exception;
	public Recharge selectOne(Integer rgid)  throws Exception;
	public void insertRecharge(Recharge recharge) throws Exception;
	public void updateRecharge(Recharge recharge) throws AlreadyPassException,Exception;
	public void passRecharge(Integer rgid,Integer uid) throws AlreadyPassException, Exception;
	public void applyMoney(User user,Double applymoney) throws  Exception ;
}

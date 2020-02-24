package com.jc.service.proxy;

import java.util.List;

import com.jc.entity.Recharge;
import com.jc.entity.RecordAndTicket;
import com.jc.entity.User;
import com.jc.exception.AlreadyPassException;
import com.jc.factory.ObjectFactory;
import com.jc.service.RechargeService;
import com.jc.trans.Transaction;

public class RechargeServiceProxy implements RechargeService {
	Transaction trans = (Transaction) ObjectFactory.getObject("trans");
	RechargeService rechargeService = (RechargeService) ObjectFactory.getObject("rechargeService");
	@Override
	public List<Recharge> selectRecharge() throws Exception {
		List<Recharge> rechargeList = null;
		trans.begin();
		try {
			rechargeList = rechargeService.selectRecharge();
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		return rechargeList;
	}
	@Override
	public void insertRecharge(Recharge recharge) throws Exception {
		trans.begin();
		try {
			rechargeService.insertRecharge(recharge);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}
	@Override
	public Recharge selectOne(Integer rgid) throws Exception {
		Recharge recharge = null;
		trans.begin();
		try {
			recharge = rechargeService.selectOne(rgid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
		return recharge;
	}
	@Override
	public void updateRecharge(Recharge recharge) throws Exception  {
		trans.begin();
		try {
			rechargeService.updateRecharge(recharge);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void passRecharge(Integer rgid, Integer uid) throws  Exception {
		trans.begin();
		try {
			rechargeService.passRecharge(rgid,uid);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

	@Override
	public void applyMoney(User user, Double applymoney)throws  Exception  {
		trans.begin();
		try {
			rechargeService.applyMoney(user, applymoney);
			trans.commit();
		} catch (Exception e){
			trans.rollback();
			throw e;
		}
	}

}

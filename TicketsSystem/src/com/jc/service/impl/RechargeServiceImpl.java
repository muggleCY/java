package com.jc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jc.dao.RechargeDao;
import com.jc.dao.UserDao;
import com.jc.entity.Recharge;
import com.jc.entity.User;
import com.jc.exception.AlreadyPassException;
import com.jc.factory.ObjectFactory;
import com.jc.service.RechargeService;

public class RechargeServiceImpl implements RechargeService{
	RechargeDao rechargeDao = (RechargeDao) ObjectFactory.getObject("rechargeDao");

	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");

	@Override
	public List<Recharge> selectRecharge() {
		return rechargeDao.selectRecharge();
	}
	@Override
	public void insertRecharge(Recharge recharge) {
		rechargeDao.insertRecharge(recharge);
	}
	@Override
	public Recharge selectOne(Integer rgid) {
		Recharge recharge = null;
		recharge = rechargeDao.selectOne(rgid);
		return recharge;
	}
	@Override
	public void updateRecharge(Recharge recharge) throws AlreadyPassException {
		if(recharge.getRechargeMode() == 0){
			throw new AlreadyPassException("重复通过");
		}else{
			recharge.setRechargeMode(0);
			User user = userDao.selectUserById(recharge.getUid());
			user.setUserMoney(user.getUserMoney() + recharge.getRechargeMoney());
			userDao.updatemoney(user);
			rechargeDao.updateRecharge(recharge);
		}
	}

	@Override
	public void passRecharge(Integer rgid,Integer uid) throws AlreadyPassException {
		Recharge recharge = rechargeDao.selectOne(rgid);
		if (recharge.getRechargeMode() == 0){
			throw new AlreadyPassException("重复通过");
		}
		recharge.setRechargeMode(0);
		rechargeDao.updateRecharge(recharge);
		User user = userDao.selectUserById(uid);
		user.setUserMoney(user.getUserMoney() + recharge.getRechargeMoney());
		userDao.updatemoney(user);
	}

	@Override
	public void applyMoney(User user,Double applymoney) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Recharge recharge = new Recharge();
		recharge.setUid(user.getUid());
		recharge.setRechargeMoney(applymoney);
		recharge.setApplyTime(String.valueOf(format.format(date)));
		rechargeDao.insertRecharge(recharge);
	}


}

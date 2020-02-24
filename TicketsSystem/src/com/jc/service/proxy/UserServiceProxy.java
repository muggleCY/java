package com.jc.service.proxy;

import java.util.ArrayList;
import java.util.List;

import com.jc.entity.User;
import com.jc.exception.LoginFailException;
import com.jc.exception.UserAlreadyExistsException;
import com.jc.factory.ObjectFactory;
import com.jc.service.UserService;
import com.jc.service.impl.UserServiceImpl;
import com.jc.trans.Transaction;

public class UserServiceProxy implements UserService{
	UserService userService = (UserService) ObjectFactory.getObject("userService");
	Transaction trans = (Transaction) ObjectFactory.getObject("trans");
	@Override
	public User login(String username, String password) throws LoginFailException,Exception {
		User user = null;
		trans.begin();
		try {
			user = userService.login(username, password);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}
	@Override
	public void regist(String username, String password, String truename, String phoneNum, String idCardNum) throws UserAlreadyExistsException,Exception {
		trans.begin();
		try {
			userService.regist(username, password, truename, phoneNum, idCardNum);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	@Override
	public List<User> selectUser() throws Exception {
		List<User> user = new ArrayList<User>();
		trans.begin();
		try {
			user = userService.selectUser();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}
	@Override
	public void updateUser(User user) throws Exception {
		trans.begin();
		try {
			 userService.updateUser(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
	}
	@Override
	public User selectUserById(Integer uid) throws Exception {
		User user = null;
		trans.begin();
		try {
			user = userService.selectUserById(uid);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		}
		return user;
	}
//	@Override
//	public void updateMoney(User user) throws Exception{
//		trans.begin();
//		try {
//			userService.updateMoney(user);
//			trans.commit();
//		} catch (Exception e) {
//			trans.rollback();
//			throw e;
//		}
//	}
}

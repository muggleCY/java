package com.jc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jc.dao.UserDao;
import com.jc.entity.User;
import com.jc.exception.LoginFailException;
import com.jc.exception.MoneyNotEnoughException;
import com.jc.exception.UserAlreadyExistsException;
import com.jc.factory.ObjectFactory;
import com.jc.service.UserService;

public class UserServiceImpl implements UserService{
	UserDao userDao = (UserDao) ObjectFactory.getObject("userDao");
	//登录
	@Override
	public User login(String username, String password) throws LoginFailException {
		User user = userDao.selectUserByNameAndPwd(username, password);
		if(user == null){
			throw new LoginFailException("用户名与密码不匹配");
		}
		return user;
	}
	//
	@Override
	public void regist(String username, String password, String truename, String phoneNum, String idCardNum) throws UserAlreadyExistsException {
		User user = userDao.selectUserByPhoneAndIdCard(phoneNum, idCardNum);
		if(user != null){
			throw new UserAlreadyExistsException("用户已经存在");
		}
		userDao.addUser(username, password, truename, phoneNum, idCardNum);
	}
	@Override
	public List<User> selectUser() {
		List<User> user = new ArrayList<User>();
		user = userDao.selectUser();
		return user;
	}
	@Override
	public void updateUser(User user) {
		if(user.getUserMode() == 0){
			user.setUserMode(2);	
		}else{		
			user.setUserMode(0);	
		}
		userDao.updateUser(user);
	}
	@Override
	public User selectUserById(Integer uid) {
		User user = userDao.selectUserById(uid);
		return user;
	}

//	@Override
//	public void banOrNotUser(Integer uid) {
//		User user = userDao.selectUserById(uid);
//	}
//	@Override
//	public void updateMoney(User user) throws MoneyNotEnoughException {
//		if(user.getUserMoney() < TicketMoney){
//			throw new MoneyNotEnoughException("钱不够啦");
//		}
//		user.setUserMoney(user.getUserMoney() - TicketMoney);
//		userDao.updatemoney(user);
//	}
}

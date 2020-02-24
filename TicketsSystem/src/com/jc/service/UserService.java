package com.jc.service;

import java.util.List;

import com.jc.entity.User;
import com.jc.exception.LoginFailException;
import com.jc.exception.MoneyNotEnoughException;
import com.jc.exception.UserAlreadyExistsException;

public interface UserService {
	public User login(String username,
			String password) throws LoginFailException,Exception;
	public void regist(String username, String password,String truename,
			String phoneNum, String idCardNum) throws UserAlreadyExistsException,Exception;
	public List<User> selectUser() throws Exception;
	public void updateUser(User user) throws Exception;
	public User selectUserById(Integer uid) throws Exception;
//	public void updateMoney(User user) throws MoneyNotEnoughException, Exception;
//	public void banOrNotUser(Integer uid);
}

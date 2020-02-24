package com.jc.service;

import com.jc.exception.LoginFailException;
import com.jc.exception.UserExistException;
import com.jc.pojo.User;
import com.jc.util.Pager;

public interface UserService {
	/**
	 * 登录（username，password）
	 */
	public User login(User user) throws LoginFailException,Exception;
	/**
	 * 添加User
	 */
	public void addUser(User user) throws UserExistException,Exception;
	/**
	 * 查找所有用户
	 */
	public Pager<User> selectUserByPage(Integer pageNo, String username,String iphone);
	/**
	 * 改变用户状态
	 */
	public void modifyUserState(Integer status, User user) throws Exception;
	/**
	 * 通过id查找用户
	 */
	public User selectUserById(Integer id) throws Exception;
	/**
	 * 修改
	 */
	public void modifyUser(User user) throws Exception;
}

package com.jc.dao;

import java.util.List;
import java.util.Map;

import com.jc.pojo.User;

public interface UserDao {
	
	/**
	 * 登录（username，password）
	 */
	public User selectUserByNameAndPwd(User user);
	
	/**
	 * 添加
	 */
	public void insertUser(User user);
	/**
	 * 通过电话查找用户
	 */
	public User selectUserByIphone(String iphone);
	/**
	 * 查找用户
	 */
	public List<User> selectUserByPage(Map<String, Object> map);
	/**
	 * 查找用户总数
	 */
	public Integer countUser(Map<String , Object> map);
	/**
	 * 修改
	 */
	public void updateUser(User user);
	/**
	 * 查找一个用户
	 */
	public User selectUser(User user);
	
}

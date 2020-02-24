package com.jc.dao;

import java.util.List;

import com.jc.entity.User;

public interface UserDao {
	public List<User> selectUser();
	public User selectUserByNameAndPwd(String username,String password);
	public User selectUserByPhoneAndIdCard(String phoneNum,String idCardNum);
	public void addUser(String username,String password,String truename,String phoneNum,String idCardNum);
	public User selectUserById(Integer uid);
	public void updateUser(User user);
	public void updatemoney(User user);
}

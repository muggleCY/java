package com.jc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.UserDao;
import com.jc.pojo.User;
/**
 * 用户的数据访问层
 * @author soft01
 *
 */
@Repository
public class UserDaoImpl implements UserDao{

	private UserDao userDao;
	/**
	 * 登录（username，password）
	 */
	@Override
	public User selectUserByNameAndPwd(User user) {
		User user1 = userDao.selectUserByNameAndPwd(user);
		return user1;
	}
	/**
	 * 添加
	 */
	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}
	/**
	 * 通过电话查找用户
	 */
	@Override
	public User selectUserByIphone(String iphone) {
		User user = userDao.selectUserByIphone(iphone);
		return user;
	}

	@Override
	public User selectUser(User user) {
		return userDao.selectUser(user);
	}
	@Override
	public List<User> selectUserByPage(Map<String, Object> map) {
		return userDao.selectUserByPage(map);
	}
	@Override
	public Integer countUser(Map<String, Object> map) {
		return userDao.countUser(map);
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	@Autowired
	public void setFactory(SqlSessionFactory factory){
		//创建Dao接口的代理对象
		this.userDao = factory.openSession().getMapper(UserDao.class);
	}
}

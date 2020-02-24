package com.jc.dao.impl;

import java.util.List;

import com.jc.dao.UserDao;
import com.jc.entity.User;
import com.jc.mapper.AUserMapper;
import com.jc.mapper.TicketMapper;
import com.jc.mapper.UserMapper;
import com.jc.util.JDBCTemplate;

public class UserDaoImpl implements UserDao{
	JDBCTemplate<User> temp = new JDBCTemplate<User>();

	@Override
	public List<User> selectUser() {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,username,user_money,user_mode ")
		.append(" from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	user_mode!=1 ")
		.toString();
		return temp.selectAll(new AUserMapper(), sql);
	}
	
	@Override
	public User selectUserByPhoneAndIdCard(String phoneNum, String idCardNum) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,username,password,truename,phonenumber,idCardNum,user_money,user_mode ")
		.append(" from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	phonenumber = ? and ")
		.append(" 	idCardNum = ? ")
		.toString();
		return temp.selectOne(new UserMapper(), sql, phoneNum,idCardNum);
	}
	@Override
	public User selectUserByNameAndPwd(String username, String password) { //通过用户名和密码查询
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,username,password,truename,phonenumber,idCardNum,user_money,user_mode ")
		.append(" from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	username = ? and ")
		.append(" 	password = ? ")
		.toString();
		return temp.selectOne(new UserMapper(), sql, username,password);
	}

	@Override
	public void addUser(String username, String password, String truename, String phoneNum, String idCardNum) {
		String sql = new StringBuffer()
			.append(" insert into ")
			.append(" 	t_user ")
			.append(" 	(id,username,password,truename,phonenumber,idCardNum,user_money,user_mode) ")
			.append(" values ")
			.append(" 	(null,?,?,?,?,?,0,0) ")
			.toString();
		temp.insert(sql, username,password,truename,phoneNum,idCardNum);
	}

	@Override
	public void updateUser(User user) {
		String sql = new StringBuffer()
		.append(" update ")
		.append(" 	t_user ")
		.append(" set ")
		.append(" 	user_mode = ? ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
		temp.update(sql, user.getUserMode(),user.getUid());
	}
	@Override
	public void updatemoney(User user) {
		String sql = new StringBuffer()
				.append(" update ")
				.append(" 	t_user ")
				.append(" set ")
				.append(" 	user_money =? ")
				.append(" where ")
				.append(" 	id = ? ")
				.toString();
		temp.update(sql, user.getUserMoney(),user.getUid());
	}

	@Override
	public User selectUserById(Integer uid) {
		String sql = new StringBuffer()
		.append(" select ")
		.append(" 	id,username,password,truename,phonenumber,idCardNum,user_money,user_mode ")
		.append(" from ")
		.append(" 	t_user ")
		.append(" where ")
		.append(" 	id = ? ")
		.toString();
	return temp.selectOne(new UserMapper(), sql, uid);
	}

}

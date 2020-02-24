package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.User;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapperObject(ResultSet rs) throws Exception {
		User user = new User();
		user.setUid(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setTruename(rs.getString("truename"));
		user.setPhoneNumber(rs.getString("phonenumber"));
		user.setIdCardNum(rs.getString("idCardNum"));
		user.setUserMoney(rs.getDouble("user_money"));
		user.setUserMode(rs.getInt("user_mode"));
		return user;
	}

}

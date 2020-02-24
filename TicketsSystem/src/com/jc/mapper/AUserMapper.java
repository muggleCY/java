package com.jc.mapper;

import java.sql.ResultSet;

import com.jc.entity.User;

public class AUserMapper implements RowMapper<User>{

	@Override
	public User mapperObject(ResultSet rs) throws Exception {
		User user = new User();
		user.setUid(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setUserMoney(rs.getDouble("user_money"));
		user.setUserMode(rs.getInt("user_mode"));
		return user;
	}

}

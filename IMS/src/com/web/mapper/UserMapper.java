package com.web.mapper;


import com.web.entity.User;
import com.web.util.DateFormateUtils;

import java.sql.Date;
import java.sql.ResultSet;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapperObject(ResultSet rs) throws Exception {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserAccount(rs.getString("t_user_account"));
		user.setUserPwd(rs.getString("t_user_pwd"));
		user.setEmpNo(rs.getInt("t_emp_no"));
		user.setRoleId(rs.getInt("t_role_id"));
		user.setUserStatusId(rs.getInt("t_user_status_id"));
		user.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getTime("t_create_time")));
		return user;
	}

}

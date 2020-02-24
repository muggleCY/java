package com.web.mapper;

import com.web.util.DateFormateUtils;
import com.web.vo.UserVo;

import java.sql.ResultSet;

/**
 * @Author cy
 * @Time 19-12-16 下午6:30
 **/
public class UserVoMapper implements RowMapper<UserVo> {
    @Override
    public UserVo mapperObject(ResultSet rs) throws Exception {
        UserVo userVo = new UserVo();
        userVo.setId(rs.getInt("id"));
        userVo.setUserAccount(rs.getString("t_user_account"));
        userVo.setUserPwd(rs.getString("t_user_pwd"));
        userVo.setEmpNo(rs.getInt("A.t_emp_no"));
        userVo.setRoleId(rs.getInt("t_role_id"));
        userVo.setUserStatusId(rs.getInt("t_user_status_id"));
        userVo.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getTime("t_create_time")));
        userVo.setEmpName(rs.getString("t_emp_name"));
        userVo.setRoleName(rs.getString("t_role_name"));
        userVo.setConfigPageValue(rs.getString("t_config_page_value"));
        userVo.setEmpNoo(rs.getString("B.t_emp_no"));
        return userVo;
    }
}

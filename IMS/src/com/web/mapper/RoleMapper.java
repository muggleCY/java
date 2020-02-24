package com.web.mapper;

import com.web.entity.Role;
import com.web.util.DateFormateUtils;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @Author cy
 * @Time 19-12-13 下午2:21
 **/
public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapperObject(ResultSet rs) throws Exception {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setRoleName(rs.getString("t_role_name"));
        role.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_create_time").getTime()));
        return role;
    }
}

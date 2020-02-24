package com.web.mapper;

import com.web.entity.Permission;
import com.web.util.DateFormateUtils;

import java.sql.ResultSet;

/**
 * @Author cy
 * @Time 19-12-16 下午3:01
 **/
public class PermissionMapper implements RowMapper<Permission>{
    @Override
    public Permission mapperObject(ResultSet rs) throws Exception {
        Permission permission = new Permission();
        permission.setId(rs.getInt("id"));
        permission.setRoleId(rs.getInt("t_role_id"));
        permission.setMenuId(rs.getInt("t_menu_id"));
        permission.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_create_time")));
        return permission;
    }
}

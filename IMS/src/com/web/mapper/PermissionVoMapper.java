package com.web.mapper;

import com.web.util.DateFormateUtils;
import com.web.vo.PermissionVo;

import java.sql.ResultSet;

/**
 * @Author cy
 * @Time 19-12-16 上午9:41
 **/
public class PermissionVoMapper implements RowMapper<PermissionVo>{
    @Override
    public PermissionVo mapperObject(ResultSet rs) throws Exception {
        PermissionVo permissionVo = new PermissionVo();
        permissionVo.setId(rs.getInt("id"));
        permissionVo.setRoleId(rs.getInt("t_role_id"));
        permissionVo.setMenuId(rs.getInt("t_menu_id"));
        permissionVo.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_create_time")));
        permissionVo.setRoleName(rs.getString("t_role_name"));
        permissionVo.setMenuName(rs.getString("t_menu_name"));
        return permissionVo;
    }
}

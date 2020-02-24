package com.web.dao;

import com.web.entity.Role;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-13 下午2:27
 **/
public interface RoleDao {
    public List<Role> selectRoles();
    public Role selectOneRole(Integer id);
    public Role selectOneRole(String roleName);
    public void deleteRole(Integer id);
    public void updateRole(Role role);
    public void insertRole(String roleName);
}

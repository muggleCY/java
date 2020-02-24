package com.web.service;

import com.web.entity.Role;
import com.web.exception.RoleAlreadyExistException;
import com.web.exception.RoleDeleteFailException;

import java.util.List;

public interface RoleService {
    public List<Role> selectRoles();
    public void deleteRole(Integer id) throws RoleDeleteFailException;
    public Role selectOneRole(Integer id);
    public void updateRole(Integer id, String roleName) throws RoleAlreadyExistException;
    void addRole(String roleName) throws RoleAlreadyExistException,Exception;
}

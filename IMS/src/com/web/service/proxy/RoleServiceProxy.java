package com.web.service.proxy;

import com.web.entity.Role;
import com.web.exception.RoleAlreadyExistException;
import com.web.exception.RoleDeleteFailException;
import com.web.service.RoleService;
import com.web.trans.Transaction;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-13 下午2:44
 **/
public class RoleServiceProxy implements RoleService {
    private RoleService roleService;
    private Transaction trans;
    @Override
    public List<Role> selectRoles() {
        List<Role> roles = roleService.selectRoles();
        return roles;
    }

    @Override
    public void deleteRole(Integer id) throws RoleDeleteFailException {
        roleService.deleteRole(id);
    }

    @Override
    public Role selectOneRole(Integer id) {
        Role role = roleService.selectOneRole(id);
        return role;
    }

    @Override
    public void updateRole(Integer id,String roleName) throws RoleAlreadyExistException {
        roleService.updateRole(id,roleName);
    }

    @Override
    public void addRole(String roleName) throws RoleAlreadyExistException,Exception {
        roleService.addRole(roleName);
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }
}

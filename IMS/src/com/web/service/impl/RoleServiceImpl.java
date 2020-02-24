package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.RoleDao;
import com.web.dao.UserDao;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.exception.RoleAlreadyExistException;
import com.web.exception.RoleDeleteFailException;
import com.web.service.RoleService;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-13 下午2:42
 **/
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private UserDao userDao;
    @Override
    public List<Role> selectRoles() {
        List<Role> roles = roleDao.selectRoles();
        return roles;
    }

    @Override
    public void deleteRole(Integer id) throws RoleDeleteFailException {
        //先通过角色id去查用户表（权限表）
        //如果有该角色的用户就抛异常不让删除
        //--如果有该角色的权限就抛出异常不让删除(没加）
        List<User> users = userDao.selectUsersByRoleId(id);
        System.out.println(users.size());
        if(users.size() != 0){
            throw new RoleDeleteFailException(ExceptionConstant.ROLE_HAVE_USER);
        }
        roleDao.deleteRole(id);



    }

    @Override
    public Role selectOneRole(Integer id) {
        Role role = roleDao.selectOneRole(id);
        return role;
    }

    @Override
    public void updateRole(Integer id,String roleName) throws RoleAlreadyExistException {
        //如果要修改角色,那么角色关联的user表,和权限表也需要更新
        if(roleDao.selectOneRole(roleName) !=null){
            throw new RoleAlreadyExistException(ExceptionConstant.ROLE_ALREADY_EXIST);
        }
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        roleDao.updateRole(role);
    }

    @Override
    public void addRole(String roleName) throws RoleAlreadyExistException,Exception {
        //查询该角色时候已经存在,如果是,抛出异常
        if(roleDao.selectOneRole(roleName) != null){
            throw new RoleAlreadyExistException(ExceptionConstant.ROLE_ALREADY_EXIST);
        }
        roleDao.insertRole(roleName);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

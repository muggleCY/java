package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.PermissionDao;
import com.web.entity.Permission;
import com.web.exception.PermissionDeleteFailException;
import com.web.exception.PremissionAlreadyExistException;
import com.web.service.PermissionService;
import com.web.trans.Transaction;
import com.web.util.Pager;
import com.web.vo.PermissionVo;

import java.util.List;

/**
 * @Author cy
 * @Time 19-12-16 上午10:01
 **/
public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao;

    @Override
    public Pager<PermissionVo> selectAllPerByPage(Integer roleId, Integer menuId, Integer pageNo) {
        Pager<PermissionVo> pager = new Pager<PermissionVo>();
        pager.setPageNo(pageNo);
        Integer totalCount = permissionDao.countPer(roleId,menuId);
        if(totalCount == null){
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, Constants.PAGE_SIZE_3);
        List<PermissionVo> permissionVos = permissionDao.selectAllPerByPage(roleId,menuId,pageNo,Constants.PAGE_SIZE_3);
        pager.setList(permissionVos);
        return pager;
    }

    @Override
    public void deletePer(Integer id) throws PermissionDeleteFailException{
        //当删除的是密码重置或者是退出系统时，不能删除
        if(id == 9||id == 12){
            throw new PermissionDeleteFailException(ExceptionConstant.PERMISS_DELETE_FAIL);
        }
        permissionDao.deletePer(id);
    }

    @Override
    public Permission selectOnePer(Integer id) {
        Permission permission = permissionDao.selectOnePerById(id);
        return permission;
    }

    @Override
    public void insert(Permission permission) throws PremissionAlreadyExistException {
        //权限重复添加,抛出异常
        Permission permission1 = permissionDao.selectOnePer(permission.getRoleId(),permission.getMenuId());
        if(permission1 != null){
            throw new PremissionAlreadyExistException(ExceptionConstant.PERMISS_ALREADY_EXIST);
        }
        permissionDao.insertPer(permission);
    }

    @Override
    public void updatePer(Integer id, Integer roleId, Integer menuId) throws PremissionAlreadyExistException {
        Permission permission = permissionDao.selectOnePer(roleId,menuId);
        if(permission != null){
            throw new PremissionAlreadyExistException(ExceptionConstant.PERMISS_ALREADY_EXIST);
        }
        permissionDao.updatePer(id,roleId,menuId);
    }

    public PermissionDao getPermissionDao() {
        return permissionDao;
    }

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }
}

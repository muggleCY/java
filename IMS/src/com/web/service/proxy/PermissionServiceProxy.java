package com.web.service.proxy;

import com.web.entity.Permission;
import com.web.exception.PermissionDeleteFailException;
import com.web.exception.PremissionAlreadyExistException;
import com.web.service.PermissionService;
import com.web.trans.Transaction;
import com.web.util.Pager;
import com.web.vo.PermissionVo;

/**
 * @Author cy
 * @Time 19-12-16 上午10:07
 **/
public class PermissionServiceProxy implements PermissionService {
    private PermissionService permissionService;
    private Transaction trans;
    @Override
    public Pager<PermissionVo> selectAllPerByPage(Integer roleId, Integer menuId, Integer pageNo) {
        Pager<PermissionVo> pager = permissionService.selectAllPerByPage(roleId,menuId,pageNo);
        return pager;
    }

    @Override
    public void deletePer(Integer id) throws PermissionDeleteFailException {
        permissionService.deletePer(id);
    }

    @Override
    public Permission selectOnePer(Integer id) {
        Permission permission = permissionService.selectOnePer(id);
        return permission;
    }

    @Override
    public void insert(Permission permission) throws PremissionAlreadyExistException {
        permissionService.insert(permission);
    }

    @Override
    public void updatePer(Integer id, Integer roleId, Integer menuId) throws PremissionAlreadyExistException{
        permissionService.updatePer(id, roleId, menuId);
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }
}

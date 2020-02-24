package com.web.service;

import com.web.entity.Permission;
import com.web.exception.PermissionDeleteFailException;
import com.web.exception.PremissionAlreadyExistException;
import com.web.util.Pager;
import com.web.vo.PermissionVo;

public interface PermissionService {
    public Pager<PermissionVo> selectAllPerByPage(Integer roleId, Integer menuId, Integer pageNo);
    public void deletePer(Integer id) throws PermissionDeleteFailException;
    public Permission selectOnePer(Integer id);
    public void insert(Permission permission) throws PremissionAlreadyExistException;
    public void updatePer(Integer id, Integer roleId, Integer menuId) throws PremissionAlreadyExistException;
}

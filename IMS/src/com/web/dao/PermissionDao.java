package com.web.dao;

import com.web.entity.Permission;
import com.web.vo.PermissionVo;

import java.util.List;

public interface PermissionDao {
    public List<PermissionVo> selectAllPer();
    public Integer countPer(Integer roleId, Integer menuId);
    public List<PermissionVo> selectAllPerByPage(Integer roleId, Integer menuId, Integer pageNo, Integer pageSize);
    public Permission selectOnePer(Integer roleId, Integer menuId);
    public Permission selectOnePerById(Integer id);
    public void insertPer(Permission permission);
    public void deletePer(Integer id);
    public void updatePer(Integer id, Integer roleId, Integer menuId);
}

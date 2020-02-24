package com.web.dao;

import com.web.entity.Dept;

import java.util.List;

public interface DeptDao {
    List<Dept> selectDepts();
    Dept selectDeptById(Integer id);
    Dept selectDeptByDeptNo(String deptNo);
    Dept selectDeptByDeptName(String deptName);
    /**
     * 根据id删除部门
     * @param id
     */
    void deleteDept(Integer id);
    void insertDept(Dept dept);
    void updateDept(Dept dept);
}

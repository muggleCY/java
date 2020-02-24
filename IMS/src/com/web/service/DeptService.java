package com.web.service;

import com.web.entity.Dept;
import com.web.exception.DeptAddException;
import com.web.exception.DeptModifyException;
import com.web.exception.DetpDeleteException;
import com.web.vo.DeptVO;

import java.util.List;

/**
 * @Author cy
 * @TIME 19-12-10
 **/
public interface DeptService {
    public List<DeptVO> selectDepts() throws Exception;
    public Dept selectDeptById(Integer id) throws Exception;
    public void deleteDept(Integer id) throws DetpDeleteException,Exception;
    public void addDept(String deptName, String deptLoc, String deptMaster, String deptDesc) throws DeptAddException,Exception;
    public void updateDept(Integer id, String deptName, String deptLoc, String deptMaster, String deptDesc) throws DeptModifyException,Exception;
}

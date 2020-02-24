package com.web.dao;

import com.web.entity.Employee;
import com.web.vo.EmployeeVO;

import java.util.List;

public interface EmployeeVODao {
    List<EmployeeVO> selectAllEmp();
    List<EmployeeVO> selectAllEmpByPager(Integer pageNumber, Integer pageSize);
    List<EmployeeVO> selectAllEmpByPageAndCodi(Integer pageNumber, Integer pageSize, String curUserName,
                                               String empName);
    EmployeeVO selectEmpByEmpId(Integer id);
}

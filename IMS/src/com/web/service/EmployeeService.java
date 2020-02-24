package com.web.service;

import com.web.entity.Employee;
import com.web.entity.User;
import com.web.exception.EmpAddException;
import com.web.exception.EmpModiException;
import com.web.util.Pager;
import com.web.vo.EmployeeVO;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public interface EmployeeService {
    Pager<Employee> getEmpListByPage(Integer pageNumber);
    Pager<EmployeeVO> getEmpVOListByPage(Integer pageNumber);
    Pager<EmployeeVO> getEmpVOListByPageAndCodi(Integer pageNumber, String empName, User user);
    void addEmployee(String empName,String sex,String empMaster,String empEntryTime) throws EmpAddException,Exception;
    void removeEmployee(Integer id) throws EmpAddException,Exception;
    EmployeeVO selectOneEmp(Integer id);

    void updateEmp(String empNo,String empName, String empMaster, String entryTime) throws EmpModiException,Exception;
}

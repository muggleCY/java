package com.web.dao;

import com.web.entity.Employee;
import com.web.util.Pager;

import java.util.List;

public interface EmployeeDao {
    List<Employee> selectAllEmp();
    List<Employee> selectAllEmpByPager(Integer pageNumber, Integer pageSize);
    List<Employee> selectEmpByDeptId(Integer deptId);
    Employee selectDeptMangerByDeptId(Integer deptId);
    Employee selectEmpById(Integer id);
    Employee selectEmpByEmpName(String empName);
    Employee selectEmyByEmpNo(String empNo);
    /**
     * 修改所有非部门经理的superId
     * @param superId
     * @param deptId
     */
    void updateSuperIdByDeptId(Integer superId, Integer deptId);
    void insertEmp(Employee employee);
    void deleteEmp(Integer id);
    void updateEmp(Employee employee);
    Employee selectEmyByEmpNoAndName(String empNo, String empName);
}

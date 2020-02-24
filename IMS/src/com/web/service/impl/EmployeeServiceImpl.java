package com.web.service.impl;

import com.web.constant.Constants;
import com.web.constant.ExceptionConstant;
import com.web.dao.*;
import com.web.entity.Dept;
import com.web.entity.Employee;
import com.web.entity.User;
import com.web.exception.EmpAddException;
import com.web.exception.EmpDeleteException;
import com.web.exception.EmpModiException;
import com.web.service.EmployeeService;
import com.web.util.DateFormateUtils;
import com.web.util.GetNoUtils;
import com.web.util.NumberGenUtils;
import com.web.util.Pager;
import com.web.vo.EmployeeVO;

import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Setter
public class EmployeeServiceImpl implements EmployeeService {
    private CountDao countDao;
    private EmployeeDao employeeDao;
    private EmployeeVODao employeeVODao;
    private RoleDao roleDao;
    private DeptDao deptDao;
    private MaxNoDao maxNoDao;
    @Override
    public Pager<Employee> getEmpListByPage(Integer pageNumber) {
        Pager<Employee> pager= new Pager<>();
        List<Employee> employees = employeeDao.selectAllEmpByPager(pageNumber, Constants.PAGE_SIZE_3);
        pager.setList(employees);
        pager.setPageNo(pageNumber);
        pager.setTotalPage(countDao.countEmployee(),Constants.PAGE_SIZE_3);
        return pager;
    }

    @Override
    public Pager<EmployeeVO> getEmpVOListByPage(Integer pageNumber) {
        Pager<EmployeeVO> pager = new Pager<>();
        List<EmployeeVO> employeeVOS = employeeVODao.selectAllEmpByPager(pageNumber, Constants.PAGE_SIZE_3);
        pager.setList(employeeVOS);
        pager.setPageNo(pageNumber);
        pager.setTotalPage(countDao.countEmployeeVO(),Constants.PAGE_SIZE_3);
        return pager;
    }

    @Override
    public Pager<EmployeeVO> getEmpVOListByPageAndCodi(Integer pageNumber, String empName, User user) {
//        不同的用户看到不同的员工列表
        //如果是管理员,查看所有部门的员工'
        List<EmployeeVO> employeeVOS;
        Pager<EmployeeVO> pager = new Pager<>();
        if (roleDao.selectOneRole(user.getRoleId()).getId().equals(Constants.ADMIN_STATE)){
            employeeVOS = employeeVODao.selectAllEmpByPageAndCodi(pageNumber,
                    Constants.PAGE_SIZE_3, null, empName);
            pager.setTotalPage(countDao.countEmployeeVOAndCodi(null,empName),Constants.PAGE_SIZE_3);
        }else {
            //如果部门经理,看到本部门的员工
            employeeVOS= employeeVODao.selectAllEmpByPageAndCodi(pageNumber,
                    Constants.PAGE_SIZE_3,employeeDao.selectEmpById(user.getEmpNo()).getEmpName(), empName);
            pager.setTotalPage(countDao.countEmployeeVOAndCodi(
                    employeeDao.selectEmpById(user.getEmpNo()).getEmpName(),empName),Constants.PAGE_SIZE_3);
        }
        pager.setList(employeeVOS);
        pager.setPageNo(pageNumber);
        //如果是普通用户,抛出异常(可不写),普通用户被权限表剔除了
        return pager;
    }

    @Override
    public void addEmployee( String empName, String sex, String empMaster, String empEntryTime) throws EmpAddException {
        //员工编号自动生成
        String empNO = GetNoUtils.getEmpNo(maxNoDao.selectEmpNoMax());
        //员工名不能重复
        if (employeeDao.selectEmpByEmpName(empName)!=null){
            throw new EmpAddException(ExceptionConstant.EMP_Name_EXISIT);
        }
        //员工的部门如果不存在,抛出异常
        Dept dept = deptDao.selectDeptByDeptName(empMaster);
        if (dept ==null){
            throw new EmpAddException(ExceptionConstant.DEPT_DOES_NOT_EXISIT);
        }
        try {
            //员工的入职时间需要format
            Employee employee = new Employee(null,empNO,empName,dept.getId(),
                    employeeDao.selectDeptMangerByDeptId(dept.getId()).getId(),sex,null,null,null,
                    empEntryTime,
                    DateFormateUtils.getNowTime(),Constants.EMP_STATE_NORMAL);
            employeeDao.insertEmp(employee);
        }catch (Exception e){
            throw new EmpAddException(ExceptionConstant.SYS_ERROR);
        }
    }


    @Override
    public void removeEmployee(Integer id) throws EmpDeleteException,Exception {
//        如果是部门经理,则不能删除该员工,提示需要需要先删除部门
        Employee employee = employeeDao.selectEmpById(id);
        if (employee.getEmpSuperId()==null){
            throw  new EmpDeleteException(ExceptionConstant.EMP_DELTE_FAIL);
        }
        //如果不是,去设置员工的state为delete
        employee.setEmpState(Constants.EMP_STATE_DELETE);
        //同时设置员工的部门Id为null
        employee.setEmpDeptId(null);
        //设置员工的上级领导为null
        employee.setEmpSuperId(null);
        employeeDao.updateEmp(employee);
    }

    @Override
    public EmployeeVO selectOneEmp(Integer id) {
        return employeeVODao.selectEmpByEmpId(id);
    }

    @Override
    public void updateEmp(String empNo,String empName, String empMaster, String entryTime) throws EmpModiException, Exception {
        //查询部门是否存在，不存在，抛出异常
        Dept dept = deptDao.selectDeptByDeptName(empMaster);
        if (dept==null){
            throw new EmpModiException(ExceptionConstant.DEPT_DOES_NOT_EXISIT);
        }
//        Employee employee = employeeDao.selectEmpByEmpName(empName);
        Employee employee = employeeDao.selectEmyByEmpNo(empNo);

        employee.setEmpName(empName);
        //如果该员工是部门经理,不允许修改
        if (employee.getEmpSuperId()==null && employee.getEmpDeptId()!=null){
            throw new EmpModiException(ExceptionConstant.EMP_MANAGER_FORBI_MODIFY_DEPT);
        }
        employee.setEntryTime(entryTime);
        employee.setEmpDeptId(dept.getId());
        employee.setEmpSuperId(employeeDao.selectDeptMangerByDeptId(dept.getId()).getId());
        employeeDao.updateEmp(employee);
    }
}

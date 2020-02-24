package com.web.service.proxy;

import com.web.entity.Employee;
import com.web.entity.User;
import com.web.exception.EmpModiException;
import com.web.service.EmployeeService;
import com.web.trans.Transaction;
import com.web.util.Pager;
import com.web.vo.EmployeeVO;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Setter
public class EmpServiceProxy implements EmployeeService {
    private EmployeeService employeeService;
    private Transaction trans;
    @Override
    public Pager<Employee> getEmpListByPage(Integer pageNumber) {
        return null;
    }

    @Override
    public Pager<EmployeeVO> getEmpVOListByPage(Integer pageNumber) {
        return null;
    }

    @Override
    public Pager<EmployeeVO> getEmpVOListByPageAndCodi(Integer pageNumber, String empName, User user) {
        return null;
    }

    @Override
    public void addEmployee(String empName, String sex, String empMaster, String empEntryTime) throws Exception {
        trans.begin();
        try {
            employeeService.addEmployee(empName,sex,empMaster,empEntryTime);;
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            throw e;
        }

    }

    @Override
    public void removeEmployee(Integer id) throws Exception {
        trans.begin();
        try {
            employeeService.removeEmployee(id);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
            throw e;
        }
    }

    @Override
    public EmployeeVO selectOneEmp(Integer id) {
        return null;
    }

    @Override
    public void updateEmp(String empNo,String empName, String empMaster, String entryTime) throws EmpModiException, Exception {

    }
}

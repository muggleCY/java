package com.web.mapper;

import com.web.util.DateFormateUtils;
import com.web.vo.EmployeeVO;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class EmployeeVOMapper implements RowMapper<EmployeeVO> {
    @Override
    public EmployeeVO mapperObject(ResultSet rs) throws Exception {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setId(rs.getInt("A.id"));
        employeeVO.setEmpNo(rs.getString("t_emp_no"));
        employeeVO.setEmpName(rs.getString("t_emp_name"));
        employeeVO.setSex(rs.getString("t_sex"));
        employeeVO.setEntryTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_entry_time")));
        employeeVO.setDeptName(rs.getString("t_dept_name"));
        employeeVO.setEmpState(rs.getInt("t_emp_state"));
        return employeeVO;
    }
}

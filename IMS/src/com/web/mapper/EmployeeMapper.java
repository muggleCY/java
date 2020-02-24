package com.web.mapper;

import com.web.entity.Employee;
import com.web.util.DateFormateUtils;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapperObject(ResultSet rs) throws Exception {
//        id, t_emp_no, t_emp_name, t_emp_dept_id, t_emp_super_id, t_sex, t_education, t_email, t_phone, t_entry_time, t_create_time
        Employee employee = new Employee(rs.getInt("id"),
                rs.getString("t_emp_no"),
                rs.getString("t_emp_name"),
                (Integer) rs.getObject("t_emp_dept_id"),
                (Integer) rs.getObject("t_emp_super_id"),
                rs.getString("t_sex"),
                rs.getString("t_education"),
                rs.getString("t_email"),
                rs.getString("t_phone"),
                DateFormateUtils.getStringTimeFromObj(rs.getTimestamp("t_entry_time")),
                DateFormateUtils.getStringTimeFromObj(rs.getTimestamp("t_create_time")),
                rs.getInt("t_emp_state")
        );
        return employee;
    }
}

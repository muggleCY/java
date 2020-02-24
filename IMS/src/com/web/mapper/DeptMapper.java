package com.web.mapper;

import com.web.entity.Dept;
import com.web.util.DateFormateUtils;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @Author cy
 * @TIME 19-12-10
 **/
public class  DeptMapper implements RowMapper<Dept>{
    public Dept mapperObject(ResultSet rs) throws Exception {
        Dept dept = new Dept();
        dept.setId(rs.getInt("id"));
        dept.setDeptNo(rs.getString("t_dept_no"));
        dept.setDeptName(rs.getString("t_dept_name"));
        dept.setDeptLoc(rs.getString("t_dept_loc"));
        dept.setDeptDesc(rs.getString("t_dept_desc"));
        dept.setDeptCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getTimestamp("t_create_time")));
        return dept;
    }
}

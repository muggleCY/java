package com.web.mapper;

import com.web.util.DateFormateUtils;
import com.web.vo.DeptVO;

import java.sql.ResultSet;

/**
 * @Author zyb
 * @Date 2019/12/15 13:41
 **/
public class DeptVOMapper implements RowMapper<DeptVO> {
    @Override
    public DeptVO mapperObject(ResultSet rs) throws Exception {
        DeptVO deptVO = new DeptVO();
        deptVO.setId(rs.getInt("id"));
        deptVO.setDeptNo(rs.getString("t_dept_no"));
        deptVO.setDeptName(rs.getString("t_dept_name"));
        deptVO.setDeptLoc(rs.getString("t_dept_loc"));
        deptVO.setDeptDesc(rs.getString("t_dept_desc"));
        deptVO.setDeptCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getTimestamp("t_create_time")));
        deptVO.setDeptManagerName(rs.getString("B.t_emp_name"));
        return deptVO;
    }
}

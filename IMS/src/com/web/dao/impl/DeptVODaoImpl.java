package com.web.dao.impl;

import com.web.dao.DeptVODao;
import com.web.mapper.DeptVOMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.DeptVO;

import java.util.List;

/**
 * @Author zyb
 * @Date 2019/12/15 13:40
 **/
public class DeptVODaoImpl implements DeptVODao {
    JDBCTemplate<DeptVO> template = new JDBCTemplate<>();
    @Override
    public List<DeptVO> getAllDept() {
//        select A.*,B.t_emp_name from ims.t_dept A,ims.t_employee B where
//        B.t_emp_dept_id = A.id and B.t_emp_super_id is null
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_dept_no,A.t_dept_name,A.t_dept_loc,A.t_dept_desc,A.t_create_time,B.t_emp_name ")
                .append(" from ")
                .append("	ims.t_dept A,ims.t_employee B ")
                .append(" where ")
                .append("   B.t_emp_dept_id = A.id")
                .append(" and B.t_emp_super_id is null ")
                .toString();
        return template.selectAll(new DeptVOMapper(),sql);
    }

    @Override
    public DeptVO selectDeptById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_dept_no,A.t_dept_name,A.t_dept_loc,A.t_dept_desc,A.t_create_time,B.t_emp_name ")
                .append(" from ")
                .append("	ims.t_dept A,ims.t_employee B ")
                .append(" where ")
                .append("   B.t_emp_dept_id = A.id")
                .append(" and B.t_emp_super_id is null ")
                .append(" and A.id = ?")
                .toString();
        return template.selectOne(new DeptVOMapper(),sql,id);
    }
}

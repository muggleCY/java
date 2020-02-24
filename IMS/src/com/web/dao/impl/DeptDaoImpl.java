package com.web.dao.impl;

import com.web.dao.DeptDao;
import com.web.entity.Dept;
import com.web.mapper.DeptMapper;
import com.web.util.JDBCTemplate;

import java.util.List;

/**
 * @Author cy
 * @TIME 19-12-10
 **/
public class DeptDaoImpl implements DeptDao {
    JDBCTemplate<Dept> temp = new JDBCTemplate<Dept>();
    public List<Dept> selectDepts() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time ")
                .append(" from ")
                .append("	t_dept ")
                .toString();
        return temp.selectAll(new DeptMapper(), sql);
    }

    @Override
    public Dept selectDeptById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time ")
                .append(" from ")
                .append("	t_dept ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        return temp.selectOne(new DeptMapper(),sql,id);
    }

    @Override
    public Dept selectDeptByDeptNo(String deptNo) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time ")
                .append(" from ")
                .append("	t_dept ")
                .append(" where ")
                .append("	t_dept_no = ? ")
                .toString();
        return temp.selectOne(new DeptMapper(),sql,deptNo);
    }

    @Override
    public Dept selectDeptByDeptName(String deptName) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	id,t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time ")
                .append(" from ")
                .append("	t_dept ")
                .append(" where ")
                .append("	t_dept_name = ? ")
                .toString();
        return temp.selectOne(new DeptMapper(),sql,deptName);
    }

    public void deleteDept(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("	t_dept ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.delete(sql, id);
    }
    public void insertDept(Dept dept) {
        String sql = new StringBuffer()
                .append(" insert into ")
                .append("	t_dept(t_dept_no,t_dept_name,t_dept_loc,t_dept_desc,t_create_time) ")
                .append(" values ")
                .append("	(?,?,?,?,?) ")
                .toString();
        temp.insert(sql, dept.getDeptNo(),dept.getDeptName(),dept.getDeptLoc(), dept.getDeptDesc(),dept.getDeptCreateTime());
    }
    public void updateDept(Dept dept) {
        String sql = new StringBuffer()
                .append(" update ")
                .append("	t_dept ")
                .append(" set ")
                .append("   t_dept_no=? ,")
                .append("	t_dept_name = ?, ")
                .append("	t_dept_loc = ?, ")
                .append("	t_dept_desc = ?, ")
                .append("	t_create_time = ? ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        temp.update(sql, dept.getDeptNo(),dept.getDeptName(),dept.getDeptLoc(), dept.getDeptDesc(),dept.getDeptCreateTime(),
                dept.getId());
    }

}

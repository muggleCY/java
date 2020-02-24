package com.web.dao.impl;

import com.web.dao.EmployeeVODao;
import com.web.mapper.EmployeeVOMapper;
import com.web.util.JDBCTemplate;
import com.web.vo.EmployeeVO;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class EmployeeVODaoImpl implements EmployeeVODao {
    JDBCTemplate<EmployeeVO> template = new JDBCTemplate<>();
    @Override
    public List<EmployeeVO> selectAllEmp() {
//        select A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,B.t_dept_name from ims.t_employee as A,ims.t_dept as B where A.t_emp_dept_id=B.id;
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,A.t_emp_state,B.t_dept_name ")
                .append(" from ")
                .append("	t_employee as A,t_dept as B ")
                .append(" where ")
                .append("   A.t_emp_dept_id=B.id")
                .append(" and t_emp_state = 0 ")
                .toString();
        return template.selectAll(new EmployeeVOMapper(),sql);
    }

    @Override
    public List<EmployeeVO> selectAllEmpByPager(Integer pageNumber, Integer pageSize) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,A.t_emp_state,B.t_dept_name ")
                .append(" from ")
                .append("	t_employee as A,t_dept as B ")
                .append(" where ")
                .append("   A.t_emp_dept_id=B.id")
                .append(" and t_emp_state = 0 ")
                .append(" limit ?,? ")
                .toString();
        return template.selectAll(new EmployeeVOMapper(),sql,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<EmployeeVO> selectAllEmpByPageAndCodi(Integer pageNumber, Integer pageSize, String curUserName,String empName) {
//        select A.id,A.t_emp_name,A.t_sex,A.t_entry_time,B.t_dept_name from ims.t_employee A,ims.t_dept B
//        where 1=1
//        and A.t_emp_super_id = (select id from ims.t_employee where t_emp_name='xxx' and t_emp_super_id is null)
//        and A.t_emp_dept_id = B.id
//        ;
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,A.t_emp_state,B.t_dept_name ")
                .append(" from ")
                .append("	ims.t_employee A,ims.t_dept B ")
                .append(" where 1=1 ");
        sql.append(" and t_emp_state = 0 ");
        List obj = new ArrayList();
        if (curUserName!=null&&curUserName!=""){
            sql.append(" and A.t_emp_super_id = ")
                    .append(" (select id from ims.t_employee where t_emp_name=? and t_emp_super_id is null) ");
            obj.add(curUserName);
        }
        if (empName!=null&&empName!=""){
            sql.append(" and A.t_emp_name= ? ");
            obj.add(empName);
        }
                sql.append(" and   A.t_emp_dept_id=B.id ")
                .append(" limit ?,? ");
        obj.add((pageNumber-1)*pageSize);
        obj.add(pageSize);
        return template.selectAll(new EmployeeVOMapper(),sql.toString(),obj.toArray());
    }

    @Override
    public EmployeeVO selectEmpByEmpId(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	A.id,A.t_emp_no,A.t_emp_name,A.t_sex,A.t_entry_time,A.t_emp_state,B.t_dept_name ")
                .append(" from ")
                .append("	t_employee as A,t_dept as B ")
                .append(" where ")
                .append("   A.t_emp_dept_id=B.id ")
                .append(" and t_emp_state = 0 ")
                .append(" and A.id = ?  ")
                .toString();
        return template.selectOne(new EmployeeVOMapper(),sql,id);
    }
}

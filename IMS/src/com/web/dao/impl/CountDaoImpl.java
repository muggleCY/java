package com.web.dao.impl;

import com.web.dao.CountDao;
import com.web.mapper.CountMapper;
import com.web.mapper.EmployeeVOMapper;
import com.web.util.JDBCTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
public class CountDaoImpl implements CountDao {
    JDBCTemplate<Integer> temp = new JDBCTemplate<Integer>();
    @Override
    public Integer countEmployee() {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	count(id) as nums ")
                .append(" from ")
                .append(" 	t_employee ")
                .append(" where t_emp_state = 0 ");
        return temp.selectOne(new CountMapper(),sql.toString());
    }

    @Override
    public Integer countEmployeeVO() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	count(*) as nums ")
                .append(" from ")
                .append("	t_employee as A,t_dept as B ")
                .append(" where ")
                .append("   A.t_emp_dept_id=B.id")
                .append(" t_emp_state = 0 ")
                .toString();
        return temp.selectOne(new CountMapper(),sql);
    }

    @Override
    public Integer countExpenseVO() {
        String sql = new StringBuffer()
                .append(" select ")
                .append(" 	count(*) as nums ")
                .append(" from ")
                .append("	ims.t_expense A,ims.t_employee B,ims.t_sys_config C,ims.t_sys_config D ")
                .append(" where ")
                .append("   A.t_req_person_id = B. id ")
                .append(" and A.t_expense_type_id = C.t_config_key")
                .append(" and A.t_req_state = D.t_config_key")
                .toString();
        return temp.selectOne(new CountMapper(),sql);
    }

    @Override
    public Integer countEmployeeVOAndCodi(String curUserName, String empName) {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	count(*) as nums ")
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
        sql.append(" and  A.t_emp_dept_id=B.id");
        return temp.selectOne(new CountMapper(),sql.toString(),obj.toArray());
    }
}

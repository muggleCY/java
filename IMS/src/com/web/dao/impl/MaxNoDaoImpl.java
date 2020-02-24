package com.web.dao.impl;

import com.web.dao.MaxNoDao;
import com.web.mapper.MaxNoMapper;
import com.web.util.JDBCTemplate;

/**
 * @Author zyb
 * @Date 2019/12/17 23:59
 **/
public class MaxNoDaoImpl implements MaxNoDao {
    JDBCTemplate<Integer> temp = new JDBCTemplate<Integer>();
    @Override
    public Integer selectEmpNoMax() {
//        select max(convert(substring(t_emp_no,2,length(t_emp_no)-1), SIGNED)) from  ims.t_employee
        //SIGNED表示整数
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	max(convert(substring(t_emp_no,2,length(t_emp_no)-1), SIGNED)) as num ")
                .append(" from ")
                .append(" 	t_employee ");
        return temp.selectOne(new MaxNoMapper(),sql.toString());
    }

    @Override
    public Integer selectDeptNoMax() {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	max(convert(substring(t_dept_no,2,length(t_dept_no)-1), SIGNED)) as num ")
                .append(" from ")
                .append(" 	t_dept ");
        return temp.selectOne(new MaxNoMapper(),sql.toString());
    }

    @Override
    public Integer selectExpenseNoMax() {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	max(convert(substring(t_expense_num,3,length(t_expense_num)-1), SIGNED)) as num ")
                .append(" from ")
                .append(" 	t_expense ");
        return temp.selectOne(new MaxNoMapper(),sql.toString());
    }

    @Override
    public Integer selectHolidayNoMax() {
        StringBuffer sql = new StringBuffer()
                .append(" select ")
                .append(" 	max(convert(substring(t_holiday_no,3,length(t_holiday_no)-1), SIGNED)) as num ")
                .append(" from ")
                .append(" 	t_holiday ");
        return temp.selectOne(new MaxNoMapper(),sql.toString());
    }
}

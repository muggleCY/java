package com.web.mapper;

import com.web.util.DateFormateUtils;
import com.web.vo.ExpenseVO;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public class ExpenseVOMapper implements RowMapper<ExpenseVO> {
    @Override
    public ExpenseVO mapperObject(ResultSet rs) throws Exception {
        ExpenseVO expenseVO = new ExpenseVO();
        expenseVO.setId( rs.getInt("A.id"));
        expenseVO.setExpenseNum(rs.getString("A.t_expense_num"));
        expenseVO.setReqPersonId(rs.getInt("A.t_req_person_id"));
        expenseVO.setExpenseTypeId(rs.getInt("A.t_expense_type_id"));
        expenseVO.setExpenseMoney( rs.getDouble("A.t_expense_money"));
        expenseVO.setReqTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("A.t_req_time")));
        expenseVO.setReqState(rs.getInt("A.t_req_state"));
        expenseVO.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("A.t_create_time")));
        expenseVO.setSummaryExp(rs.getString("A.t_summary_exp"));
        expenseVO.setReqPerName(rs.getString("B.t_emp_name"));
        expenseVO.setExpenseTypeName(rs.getString("C.t_config_page_value"));
        expenseVO.setReqStateName(rs.getString("D.t_config_page_value"));
        return expenseVO;
    }
}

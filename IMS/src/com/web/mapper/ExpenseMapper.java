package com.web.mapper;

import com.web.entity.Expense;
import com.web.util.DateFormateUtils;
import com.web.vo.ExpenseVO;

import java.sql.ResultSet;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public class ExpenseMapper implements RowMapper<Expense> {
    @Override
    public Expense mapperObject(ResultSet rs) throws Exception {
        Expense expense = new Expense();
        expense.setId( rs.getInt("id"));
        expense.setExpenseNum(rs.getString("t_expense_num"));
        expense.setReqPersonId(rs.getInt("t_req_person_id"));
        expense.setExpenseTypeId(rs.getInt("t_expense_type_id"));
        expense.setExpenseMoney( rs.getDouble("t_expense_money"));
        expense.setReqTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_req_time")));
        expense.setReqState(rs.getInt("t_req_state"));
        expense.setCreateTime(DateFormateUtils.getStringTimeFromObj(rs.getDate("t_create_time")));
        expense.setSummaryExp(rs.getString("t_summary_exp"));
        return expense;
    }
}

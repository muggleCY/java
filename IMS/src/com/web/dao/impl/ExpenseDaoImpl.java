package com.web.dao.impl;

import com.web.dao.ExpenseDao;
import com.web.entity.Dept;
import com.web.entity.Expense;
import com.web.mapper.ExpenseMapper;
import com.web.util.JDBCTemplate;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public class ExpenseDaoImpl implements ExpenseDao {
    JDBCTemplate<Expense> template = new JDBCTemplate<>();
    @Override
    public void updateExpense(Expense expense) {
            String sql = new StringBuffer()
                    .append(" update ")
                    .append("	t_expense ")
                    .append(" set ")
                    .append("	t_expense_type_id = ?, ")
                    .append("	t_expense_money = ?, ")
                    .append("	t_req_time = ?, ")
                    .append("	t_req_state = ?, ")
                    .append("	t_create_time = ? ")
                    .append(" where ")
                    .append("	id = ? ")
                    .toString();
            template.update(sql,expense.getExpenseTypeId(),expense.getExpenseMoney(),expense.getReqTime(),expense.getReqState(),expense.getCreateTime(),expense.getId());
    }

    @Override
    public void deleteExpense(Integer id) {
        String sql = new StringBuffer()
                .append(" delete from ")
                .append("	t_expense ")
                .append(" where ")
                .append("	id = ? ")
                .toString();
        template.delete(sql,id);
    }

    @Override
    public void addExpense(Expense expense) {
//        t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state, t_create_time
            String sql = new StringBuffer()
                    .append(" insert into ")
                    .append("	t_expense(t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state, t_create_time,t_summary_exp) ")
                    .append(" values ")
                    .append("	(?,?,?,?,?,?,?,?) ")
                    .toString();
            template.insert(sql,
                    expense.getExpenseNum(),
                    expense.getReqPersonId(),
                    expense.getExpenseTypeId(),
                    expense.getExpenseMoney(),
                    expense.getReqTime(),
                    expense.getReqState(),
                    expense.getCreateTime(),
                    expense.getSummaryExp());
    }

    @Override
    public Expense selectExpenseById(Integer id) {
        String sql = new StringBuffer()
                .append(" select ")
                .append("	id,t_expense_num, t_req_person_id, t_expense_type_id, t_expense_money, t_req_time, t_req_state, t_create_time,t_summary_exp ")
                .append(" from ")
                .append(" t_expense ")
                .append(" where t_expense_num = ? ")
                .toString();
        return template.selectOne(new ExpenseMapper(),sql,id);
    }
}

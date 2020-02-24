package com.jc.dao;

import java.util.List;

import com.jc.pojo.Expense;
import com.jc.vo.ExpenseVo;

public interface ExpenseDao {
	
	public void insertExpense(Expense expense);
	
	public void updateExpense(Expense expense);
	
	public List<Expense> selectExpenses();
	
	public List<ExpenseVo> selectExpenseVo(Integer userId);
	
	public Expense selectExpenseByUidAndGid(Expense expense);
}

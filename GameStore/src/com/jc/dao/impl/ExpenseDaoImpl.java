package com.jc.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.ExpenseDao;
import com.jc.pojo.Expense;
import com.jc.vo.ExpenseVo;

@Repository
public class ExpenseDaoImpl implements ExpenseDao{
	
	private ExpenseDao expenseDao;
	
	@Override
	public void insertExpense(Expense expense) {
		expenseDao.insertExpense(expense);
	}
	
	@Override
	public void updateExpense(Expense expense) {
		expenseDao.updateExpense(expense);
	}
	
	@Override
	public List<Expense> selectExpenses() {
		return expenseDao.selectExpenses();
	}
	
	@Override
	public List<ExpenseVo> selectExpenseVo(Integer userId) {
		return expenseDao.selectExpenseVo(userId);
	}
	
	@Override
	public Expense selectExpenseByUidAndGid(Expense expense) {
		return expenseDao.selectExpenseByUidAndGid(expense);
	}
	
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.expenseDao = factory.openSession().getMapper(ExpenseDao.class);
	}
}

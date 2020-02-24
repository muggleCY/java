package com.jc.service;

import java.util.List;

import com.jc.pojo.Expense;
import com.jc.vo.ExpenseVo;


public interface ExpenseService {

	public void addExpense(Integer userId,Integer gameId,Integer payWay) throws Exception;
	
	public void modifyExpense(Expense expense) throws Exception;
	
	public List<Expense> selectExpenses();
	
	public List<ExpenseVo> showExpenseVo(Integer userId);
	
	public Expense selectExpenseByUidAndGid(Integer userId,Integer gameId);
}

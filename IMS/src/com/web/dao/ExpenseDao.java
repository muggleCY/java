package com.web.dao;

import com.web.entity.Expense;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public interface ExpenseDao {
    void updateExpense(Expense expense);
    void deleteExpense(Integer id);
    void addExpense(Expense expense);
    Expense selectExpenseById(Integer id);
}

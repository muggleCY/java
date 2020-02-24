package com.web.dao;

public interface CountDao {
	Integer countEmployee();
	Integer countEmployeeVO();
	Integer countExpenseVO();
	Integer countEmployeeVOAndCodi(String curUserName,String empName);
}

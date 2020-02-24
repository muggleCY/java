package com.jc.trans.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.jc.trans.Transaction;
import com.jc.util.JDBCUtil;



public class TransactionImpl implements Transaction{

	@Override
	public void begin() {
		try {
			Connection conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			System.out.println("事务开启");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void commit() {
		try {
			Connection conn = JDBCUtil.getConnection();
			conn.commit();
			System.out.println("事务提交");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close();
		}
	}

	@Override
	public void rollback() {
		try {
			Connection conn = JDBCUtil.getConnection();
			conn.rollback();
			System.out.println("事务回滚");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close();
		}
		
	}




}

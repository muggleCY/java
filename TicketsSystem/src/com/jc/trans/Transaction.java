package com.jc.trans;

public interface Transaction {
	/**
	 * 开启事务
	 */
	public void begin();
	/**
	 * 提交事务
	 */
	public void commit();
	/**
	 * 回滚事务
	 */
	public void rollback();
	
}

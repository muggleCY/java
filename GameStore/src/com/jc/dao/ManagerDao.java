package com.jc.dao;

import com.jc.pojo.Manager;

public interface ManagerDao {
	/**
	 * 管理员登录
	 */
	public Manager selectManager(Manager manager);
}

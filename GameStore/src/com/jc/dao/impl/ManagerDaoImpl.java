package com.jc.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.ManagerDao;
import com.jc.pojo.Manager;
@Repository
public class ManagerDaoImpl implements ManagerDao {
	private ManagerDao managerDao;
	/**
	 * 管理员登录
	 */
	@Override
	public Manager selectManager(Manager manager) {
		return managerDao.selectManager(manager);
	}
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.managerDao = factory.openSession().getMapper(ManagerDao.class);
	}
}

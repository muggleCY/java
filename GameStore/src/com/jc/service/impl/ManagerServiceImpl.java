package com.jc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.ManagerDao;
import com.jc.exception.LoginFailException;
import com.jc.pojo.Manager;
import com.jc.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerDao managerDao;
	/**
	 * 登录
	 */
	@Override
	@Transactional
	public Manager getManager(Manager manager) throws Exception {
		Manager manager1 = managerDao.selectManager(manager);
		if (manager1 == null) {
			throw new LoginFailException(Constants.MANAGER_ERROR);
		}
		return manager1;
	}


}

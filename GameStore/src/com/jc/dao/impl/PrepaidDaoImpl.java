package com.jc.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.PrepaidDao;
import com.jc.pojo.Prepaid;
import com.jc.vo.PrepaidVo;

@Repository
public class PrepaidDaoImpl implements PrepaidDao{
	
	private PrepaidDao prepaidDao;
	
	@Override
	public void insertPrepaid(Prepaid prepaid) {
		prepaidDao.insertPrepaid(prepaid);
	}
	@Override
	public Integer selectCardId(Integer userId) {
		return prepaidDao.selectCardId(userId);
	}
	@Override
	public List<PrepaidVo> selectPrepaidVo(Integer userId) {
		return prepaidDao.selectPrepaidVo(userId);
	}
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.prepaidDao = factory.openSession().getMapper(PrepaidDao.class);
	}
}

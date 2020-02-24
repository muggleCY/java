package com.jc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.ExchangeDao;
import com.jc.pojo.Exchange;
import com.jc.vo.ExchangeVo;

@Repository
public class ExchangeDaoImpl implements ExchangeDao{
	
	private ExchangeDao exchangeDao;
	/**
	 * 分页查换算比例
	 * @param map
	 * @return
	 */
	@Override
	public List<Exchange> selectExchangesByPage(Map<String, Object> map) {
		return exchangeDao.selectExchangesByPage(map);
	}

	@Override
	public Integer countExchange(Map<String, Object> map) {
		return exchangeDao.countExchange(map);
	}

	@Override
	public List<ExchangeVo> selectExchanges(Map<String, Object> map) {
		return exchangeDao.selectExchanges(map);
	}
	/**
	 * id查换算比例
	 * @param exchange
	 * @return
	 */
	@Override
	public Exchange selectExchangeById(Integer id) {
		return exchangeDao.selectExchangeById(id);
	}
	/**
	 * 添加
	 */
	@Override
	public void insertExchange(Exchange exchange) {
		exchangeDao.insertExchange(exchange);
	}
	/**
	 * 修改
	 */
	@Override
	public void updateExchange(Exchange exchange) {
		exchangeDao.updateExchange(exchange);
	}
	/**
	 * 删除
	 */
	@Override
	public void deleteExchange(Integer id) {
		exchangeDao.deleteExchange(id);
	}


	@Override
	public Exchange selectExchangeByProvId(Exchange exchange) {
		return exchangeDao.selectExchangeByProvId(exchange);
	}
	
	@Override
	public List<Exchange> selectAllExchanges() {
		return exchangeDao.selectAllExchanges();
	}
	
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.exchangeDao = factory.openSession().getMapper(ExchangeDao.class);
	}




}

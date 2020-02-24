package com.jc.dao;

import java.util.List;
import java.util.Map;

import com.jc.pojo.Exchange;
import com.jc.vo.ExchangeVo;



public interface ExchangeDao {
	/**
	 * 分页查换算比例
	 * @param map
	 * @return
	 */
	public List<Exchange> selectExchangesByPage(Map<String, Object> map);
	public List<ExchangeVo> selectExchanges(Map<String, Object> map);

	public Integer countExchange(Map<String , Object> map);
	/**
	 * 通过id查找换算比例
	 * @param exchange
	 * @return
	 */
	public Exchange selectExchangeById(Integer id);
	/**
	 * 新增
	 * @param exchange
	 */
	public void insertExchange(Exchange exchange);
	/**
	 * 更新
	 * @param exchange
	 */
	public void updateExchange(Exchange exchange);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteExchange(Integer id);
	/**
	 * 通过省份查找换算比例
	 * @param exchange
	 * @return
	 */
	public Exchange selectExchangeByProvId(Exchange exchange);
	/**
	 * 查询所有
	 * @param exchange
	 * @return
	 */
	public List<Exchange> selectAllExchanges();
}

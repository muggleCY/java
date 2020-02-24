package com.jc.service;

import com.jc.pojo.Exchange;
import com.jc.util.Pager;
import com.jc.vo.ExchangeVo;


public interface ExchangeService {
	/**
	 * 查询所有换算比例
	 * @param pageNo
	 * @param prov
	 * @return
	 */
	public Pager<Exchange> selectExchangesByPage(Integer pageNo, Integer prov);
	public Pager<ExchangeVo> selectExchanges(Integer pageNo, Integer prov);

	public Exchange selectExchangeById(Integer id);

	public void addOrModifyExchange(Exchange exchange) throws Exception;

	public void removeExchanges(String[] ids) throws Exception;
}

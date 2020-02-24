package com.jc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.ExchangeDao;
import com.jc.exception.ExchangeAlreadyExistsException;
import com.jc.exception.ExchangeDelErrorException;
import com.jc.pojo.Exchange;
import com.jc.service.ExchangeService;
import com.jc.util.Pager;
import com.jc.vo.ExchangeVo;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	@Autowired
	private ExchangeDao exchangeDao;
	@Override
	public Pager<Exchange> selectExchangesByPage(Integer pageNo, Integer prov) {
		Pager<Exchange> pager = new Pager<Exchange>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("prov", prov);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		//找条数
		Integer totalCount = exchangeDao.countExchange(map);
		//找数据
		List<Exchange> exchanges = exchangeDao.selectExchangesByPage(map);
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(exchanges);
		return pager;
	}
	@Override
	public Pager<ExchangeVo> selectExchanges(Integer pageNo, Integer provId) {
		Pager<ExchangeVo> pager = new Pager<ExchangeVo>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("provId", provId);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		//找条数
		Integer totalCount = exchangeDao.countExchange(map);
		//找数据
		List<ExchangeVo> exchanges = exchangeDao.selectExchanges(map);
		//组装数据
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(exchanges);
		return pager;
	}
	/**
	 * id查找
	 * @param id
	 * @return
	 */
	@Override
	public Exchange selectExchangeById(Integer id) {
		return exchangeDao.selectExchangeById(id);
	}
	/**
	 * 新增/修改
	 */
	@Override
	@Transactional
	public void addOrModifyExchange(Exchange exchange) throws Exception {
		if (exchange.getId() == null) {
			//添加
			Date date = new Date();
			exchange.setCreateTime(date);
			exchange.setUpdateTime(date);
			if (exchangeDao.selectExchangeByProvId(exchange)!=null) {
//				该省份的换算比例已存在
				throw new ExchangeAlreadyExistsException(Constants.EXCHANGE_EXIST);
			}
			exchangeDao.insertExchange(exchange);
		}else{
			//修改
			Date date = new Date();
			exchange.setUpdateTime(date);
			exchangeDao.updateExchange(exchange);
		}
	}
	@Override
	public void removeExchanges(String[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			try {
				exchangeDao.deleteExchange(Integer.parseInt(ids[i]));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExchangeDelErrorException(Constants.EXCHANGE_DELETE_ERROR);
			}
		}
	}

}

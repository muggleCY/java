package com.jc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc.dao.ExchangeDao;
import com.jc.dao.ProvinceDao;
import com.jc.pojo.Exchange;
import com.jc.pojo.Province;
import com.jc.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private ExchangeDao exchangeDao;
	/**
	 * 查找所有省份
	 */
	@Override
	public List<Province> getProvinces() {
		return provinceDao.selectProvinces();
	}
	@Override
	public List<Province> getProvinceById() {
		List<Exchange> exchanges = exchangeDao.selectAllExchanges();
		String[] provIds = null;
		Integer i = 0;
		provIds = new String[exchanges.size()];
		for (Exchange exchange : exchanges) {
			provIds[i] = String.valueOf(exchange.getProvId());
			i++;
		}
		return provinceDao.getProvinceById(provIds);
	}
	@Override
	public Province getProvById(Integer id) {
		return provinceDao.getProvById(id);
	}

}

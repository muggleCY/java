package com.jc.service;

import java.util.List;

import com.jc.pojo.Province;

public interface ProvinceService {
	/**
	 * 查找所有省份
	 */
	public List<Province> getProvinces();
	public List<Province> getProvinceById();
	public Province getProvById(Integer id);
}

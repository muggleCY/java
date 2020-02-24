package com.jc.dao;

import java.util.List;

import com.jc.pojo.Province;

public interface ProvinceDao {
	/**
	 * 查找所有省份
	 */
	public List<Province> selectProvinces();
	public List<Province> getProvinceById(String[] provId);
	public Province getProvById(Integer id);
}

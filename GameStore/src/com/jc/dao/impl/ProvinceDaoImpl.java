package com.jc.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.ProvinceDao;
import com.jc.pojo.Province;
/**
 * 省份
 * @author soft01
 *
 */
@Repository
public class ProvinceDaoImpl implements ProvinceDao{
	private ProvinceDao provinceDao;
	/**
	 * 查找所有省份
	 */
	@Override
	public List<Province> selectProvinces() {
		return provinceDao.selectProvinces();
	}
	
	@Override
	public List<Province> getProvinceById(String[] provId) {
		return provinceDao.getProvinceById(provId);
	}
	
	@Override
	public Province getProvById(Integer id) {
		return provinceDao.getProvById(id);
	}
	
	@Autowired
	public void setFactory(SqlSessionFactory factory){
		this.provinceDao = factory.openSession().getMapper(ProvinceDao.class);
	}



}

package com.jc.dao;

import java.util.List;

import com.jc.pojo.Prepaid;
import com.jc.vo.PrepaidVo;

public interface PrepaidDao {
	
	public void insertPrepaid(Prepaid prepaid);
	
	public Integer selectCardId(Integer userId);
	
	public List<PrepaidVo> selectPrepaidVo(Integer userId);

}

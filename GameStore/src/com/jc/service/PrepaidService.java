package com.jc.service;

import java.util.List;

import com.jc.vo.PrepaidVo;




public interface PrepaidService {
	
	public void addPrepaid(Integer userId ,String cardNum,String cardPwd) throws Exception;
	
	public Integer selectCardId(Integer userId);
	
	public List<PrepaidVo> selectPrepaidVo(Integer userId);
	
}

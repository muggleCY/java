package com.jc.service;

import java.util.Date;

import com.jc.util.Pager;
import com.jc.vo.CardVo;


public interface CardService {
	public Pager<CardVo> selectCardsByPage(Integer pageNo, String num ,String startTime , String endTime ,Integer prov) throws Exception;

	public void addCards(Integer number, Integer[] provs, Integer cardAmount,
			String startTime, String endTime) throws Exception;
//
//	public Card selectCardByNumAndPwd(String cardNum, String cardPwd) throws Exception;
//	
//	public void updateCardAlreadyUse(Card card) throws Exception;
}

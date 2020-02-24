package com.jc.dao;

import java.util.List;
import java.util.Map;

import com.jc.pojo.Card;
import com.jc.vo.CardVo;



public interface CardDao {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<Card> selectCardByPage(Map<String, Object> map);
	public List<CardVo> selectCards(Map<String, Object> map);

	public Integer countCard(Map<String , Object> map);
	/**
	 * 添加
	 * @param card
	 */
	public void insertCard(Card card);
	/**
	 * 更新
	 * @param card
	 */
	public void updateCard(Card card);
	/**
	 * 通过卡号查/密保卡账号密码
	 * @param card
	 * @return
	 */
	public Card selectCardByNumAndPwd(Card card);

}

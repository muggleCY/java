package com.jc.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jc.dao.CardDao;
import com.jc.pojo.Card;
import com.jc.vo.CardVo;

@Repository
public class CardDaoImpl implements CardDao {
	
	private CardDao cardDao;
	@Override
	public List<Card> selectCardByPage(Map<String, Object> map) {
		return cardDao.selectCardByPage(map);
	}
	@Override
	public List<CardVo> selectCards(Map<String, Object> map) {
		return cardDao.selectCards(map);
	}

	@Override
	public Integer countCard(Map<String, Object> map) {
		return cardDao.countCard(map);
	}
	/**
	 * 添加
	 */
	@Override
	public void insertCard(Card card) {
		cardDao.insertCard(card);
	}
	/**
	 * 更新
	 */
	@Override
	public void updateCard(Card card) {
		cardDao.updateCard(card);
	}

	@Override
	public Card selectCardByNumAndPwd(Card card) {
		return cardDao.selectCardByNumAndPwd(card);
	}
	
	@Autowired
	public void setFactory(SqlSessionFactory factory) {
		this.cardDao = factory.openSession().getMapper(CardDao.class);
	}

}

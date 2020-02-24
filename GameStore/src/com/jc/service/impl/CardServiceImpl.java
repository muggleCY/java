package com.jc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.pojo.Card;
import com.jc.service.CardService;
import com.jc.util.CardUtil;
import com.jc.util.Pager;
import com.jc.vo.CardVo;
import com.jc.dao.CardDao;

@Service
public class CardServiceImpl implements CardService{

	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private CardDao cardDao;
	
	@Override
	public Pager<CardVo> selectCardsByPage(Integer pageNo, String num,
			String startTime, String endTime, Integer prov) throws Exception {
		
		Pager<CardVo> pager = new Pager<CardVo>();
		Date sTime = new Date();//开始时间
		Date eTime = new Date();//到期时间
		Date nowTime = new Date();
		if (startTime.equals("")||startTime == null) {
			sTime = null;
		}else{
			sTime = sf.parse(startTime);//将字符串转为date类型
		}
		if (endTime.equals("")||endTime == null) {
			eTime = null;
		}else{
			eTime = sf.parse(endTime);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cardNum", num);
		map.put("startTime", sTime);
		map.put("endTime", eTime);
		map.put("provId", prov);
		map.put("pageNo", pageNo);
		map.put("pageSize", 4);
		Integer totalCount = cardDao.countCard(map);
		List<Card> cards = cardDao.selectCardByPage(map);
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getCardStatus()!=4) {//card状态不是已使用的
				setStatusByNowTime(cards.get(i), cards.get(i).getStartTime(), cards.get(i).getEndTime(), nowTime);
				cardDao.updateCard(cards.get(i));
			}
		}
		List<CardVo> cardVos = cardDao.selectCards(map);
		pager.setPageNo(pageNo);
		pager.setTotalPage(totalCount, 4);
		pager.setList(cardVos);
		return pager;
	}

	@Override
	public void addCards(Integer number, Integer[] provs, Integer cardAmount,
			String startTime, String endTime) throws Exception {
		
		Card card = new Card();
		card.setCardAmount(cardAmount);
		card.setCreateTime(new Date());
		Date nowTime = new Date();
		Date sTime = sf.parse(startTime);
		Date eTime = sf.parse(endTime);
		card.setStartTime(sTime);
		card.setEndTime(eTime);
		
		setStatusByNowTime(card, sTime, eTime,nowTime);
		//随机生成卡号密码 如果重复，重新生成
		for (int i = 0; i < provs.length; i++) {
			card.setProvId(provs[i]);
			for (int j = 0; j < number; j++) {
				//随机生成卡号
				card.setCardNum(CardUtil.getRandom(12));
				while (cardDao.selectCardByNumAndPwd(card)!=null) {
					card.setCardNum(CardUtil.getRandom(10));
				}
				card.setCardPwd(CardUtil.getRandom(6));
				cardDao.insertCard(card);
			}
		}
	}

	public void setStatusByNowTime(Card card, Date sTime, Date eTime ,Date nowTime) {
		if (sTime.getTime()>nowTime.getTime()) {
			card.setCardStatus(3);//未生效
		}else if(eTime.getTime()>nowTime.getTime()){
			card.setCardStatus(1);//正常
		}else if(eTime.getTime()<nowTime.getTime()){
			card.setCardStatus(2);//过期
		}
	}
//
//	@Override
//	@Transactional
//	public Card selectCardByNumAndPwd(String cardNum, String cardPwd)
//			throws Exception {
//		Card card = new Card();
//		card.setNum(cardNum);
//		card.setPwd(cardPwd);
//		if (cardDao.selectCardByNumAndPwd(card) == null) {
////			输入的卡号或密码错误，请重新输入
//			throw new CardNoOrPwdException(Constant.CARD_NO_OR_PWD_WRONG);
//		}
//		System.out.println(cardDao.selectCardByNumAndPwd(card).getStatus()+"!!!!");
//		if (cardDao.selectCardByNumAndPwd(card).getStatus()!=1) {
////			密保卡状态异常，此卡可能已过期、未生效或者已使用
//			throw new CardStatusException(Constant.CARD_STATUS_WRONG);
//		}
//		return cardDao.selectCardByNumAndPwd(card);
//	}
//
//	@Override
//	@Transactional
//	public void updateCardAlreadyUse(Card card) throws Exception{
//		card.setStatus(4);
//		cardDao.updateCard(card);
//	}
//	
}

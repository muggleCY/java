package com.jc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.constant.Constants;
import com.jc.dao.CardDao;
import com.jc.dao.PrepaidDao;
import com.jc.dao.UserDao;
import com.jc.exception.CardNumOrPwdException;
import com.jc.exception.CardStatusException;
import com.jc.exception.UserAndCardProvNotSameException;
import com.jc.exception.UserCloseException;
import com.jc.pojo.Card;
import com.jc.pojo.Prepaid;
import com.jc.pojo.User;
import com.jc.service.PrepaidService;
import com.jc.vo.PrepaidVo;

@Service
public class PrepaidServiceImpl implements PrepaidService{
	@Autowired
	private PrepaidDao prepaidDao;
	@Autowired
	private CardDao cardDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void addPrepaid(Integer userId ,String cardNum,String cardPwd) throws Exception{
		Date nowTime = new Date();
		Card card1 = new Card();
		card1.setCardNum(cardNum);
		Card card = cardDao.selectCardByNumAndPwd(card1);
		if (card == null) {
//		输入的卡号或密码错误，请重新输入
			throw new CardNumOrPwdException(Constants.CARDNUM_ERROR);
		}
		System.out.println(card.getCardPwd()+"------");
		System.out.println(cardPwd+"+++++++++");
		if(!card.getCardPwd().equals(cardPwd)){
			throw new CardNumOrPwdException(Constants.CARDPWD_ERROR);
		}
		//先重新判断状态
		if (card.getCardStatus()!=4) {//card状态不是已使用的
			setStatusByNowTime(card, card.getStartTime(), card.getEndTime(), nowTime);
			System.out.println(card.getCardStatus()+"-------------------");
		}
		//再判断密保卡状态是否为可使用
		if (card.getCardStatus()!=1) {
			if(card.getCardStatus() == 2){
				//过期
				throw new CardStatusException(Constants.CARD_OUT_OF_DATE);
			}
			if(card.getCardStatus() == 3){
				//未生效
				throw new CardStatusException(Constants.CARD_NOT_IN_DATE);
			}
			if(card.getCardStatus() == 4){
				//已经使用
				throw new CardStatusException(Constants.CARD_ALREADY_USED);
			}
		}
		User userI = new User();
		userI.setId(userId);
		User user = userDao.selectUser(userI);
		if (!user.getProvinceId().equals(card.getProvId())) {
//			省份不一致 
			throw new UserAndCardProvNotSameException(Constants.USER_AND_CARD_PROV_NOT_SAME);
		}
		if(user.getStatus() == 2){
			throw new UserCloseException(Constants.USER_CLOSED_ERROR);
		}
		user.setCurrency(user.getCurrency() + card.getCardAmount());
		System.out.println("currency :" + user.getCurrency());
		userDao.updateUser(user);
		card.setCardStatus(4);
		cardDao.updateCard(card);
		
		
		Prepaid prepaid = new Prepaid();
		Date date = new Date();
		prepaid.setUserId(userId);
		prepaid.setCardId(card.getId());
		prepaid.setCreateTime(date);
		prepaidDao.insertPrepaid(prepaid);
	}

	@Override
	public Integer selectCardId(Integer userId) {
		return prepaidDao.selectCardId(userId);
	}

	@Override
	public List<PrepaidVo> selectPrepaidVo(Integer userId) {
		return prepaidDao.selectPrepaidVo(userId);
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
}

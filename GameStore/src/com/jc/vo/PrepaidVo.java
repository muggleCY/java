package com.jc.vo;

public class PrepaidVo {
	private Integer userId;
	private Integer cardId;
	private String username;
	private String cardNum;
	private String cardPwd;
	private Integer cardAmount;
	public PrepaidVo() {
		super();
	}

	public PrepaidVo(Integer userId, Integer cardId,
			String username, String cardNum, String cardPwd, Integer cardAmount) {
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.username = username;
		this.cardNum = cardNum;
		this.cardPwd = cardPwd;
		this.cardAmount = cardAmount;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardPwd() {
		return cardPwd;
	}
	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}
	public Integer getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(Integer cardAmount) {
		this.cardAmount = cardAmount;
	}

}

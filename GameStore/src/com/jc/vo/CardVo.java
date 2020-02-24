package com.jc.vo;

import java.util.Date;


public class CardVo {
	private Integer id;
	private String cardNum;
	private String cardPwd;
	private Integer cardAmount;
	private Integer provId;
	private Date startTime;
	private Date endTime;
	private Integer cardStatus;
	private Date createTime;
	private String provinceName;
	
	public CardVo() {
	}
	
	
	
	public CardVo(Integer id, String cardNum, String cardPwd,
			Integer cardAmount, Integer provId, Date startTime, Date endTime,
			Integer cardStatus, Date createTime, String provinceName) {
		super();
		this.id = id;
		this.cardNum = cardNum;
		this.cardPwd = cardPwd;
		this.cardAmount = cardAmount;
		this.provId = provId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cardStatus = cardStatus;
		this.createTime = createTime;
		this.setProvinceName(provinceName);
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getProvinceName() {
		return provinceName;
	}



	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


}

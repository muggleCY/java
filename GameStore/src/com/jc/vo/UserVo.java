package com.jc.vo;
/**
 * 前端用户
 * @author soft01
 *
 */
public class UserVo {
	private Integer id;
	private String username;
	private String password;
	private String iphone;
	private Integer provinceId;
	private String provinceName;
	private Double tariffe;
	private Double currency;
	private Double cardAmount;
	private Integer status;
	private String createTime;
	private Integer sex;
	
	
	public UserVo() {
	}

	
	
	public UserVo(Integer id, String username, String password, String iphone,
			Integer provinceId, String provinceName, Double tariffe,
			Double currency, Double cardAmount, Integer status,
			String createTime, Integer sex) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.iphone = iphone;
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.tariffe = tariffe;
		this.currency = currency;
		this.cardAmount = cardAmount;
		this.status = status;
		this.createTime = createTime;
		this.sex = sex;
	}



	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Double getTariffe() {
		return tariffe;
	}
	public void setTariffe(Double tariffe) {
		this.tariffe = tariffe;
	}
	public Double getCurrency() {
		return currency;
	}
	public void setCurrency(Double currency) {
		this.currency = currency;
	}
	public Double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(Double cardAmount) {
		this.cardAmount = cardAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}

package com.jc.entity;
/**
 * 用户类
 * @author soft01
 *
 */
public class User {
	private Integer uid; //编号
	private String username;//用户名
	private String password;//密码
	private String truename; //真实姓名
	private String phoneNumber;//手机号
	private String idCardNum;//身份证
	private Double userMoney;//余额
	private Integer userMode;//用户状态（0 管理员，1正常用户，2封禁用户）
	
	public User() {

	}
	
	public User(Integer uid, String username, String password, String truename, String phoneNumber, String idCardNum, Double userMoney, Integer userMode) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.truename = truename;
		this.phoneNumber = phoneNumber;
		this.idCardNum = idCardNum;
		this.userMoney = userMoney;
		this.userMode = userMode;
	}

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public Double getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(Double userMoney) {
		this.userMoney = userMoney;
	}
	public Integer getUserMode() {
		return userMode;
	}
	public void setUserMode(Integer userMode) {
		this.userMode = userMode;
	}
	
}

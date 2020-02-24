package com.web.mvc;
//	<result name="success">/qqqZoneIndex.jsp</result>
public class Result {
	/**
	 * 通过name决定方执行完后去哪个result中的地址
	 */
	private String name;
	/**
	 * 重定向和转发
	 */
	private String type;
	/**
	 * result配置的目标地址
	 */
	private String targetUrl;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	
}

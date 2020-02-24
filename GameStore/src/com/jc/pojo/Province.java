package com.jc.pojo;
/**
 * 省份
 * @author soft01
 *
 */
public class Province {
	private Integer id;
	private String provinceName;
	
	public Province() {
	}
	public Province(Integer id, String provinceName) {
		this.id = id;
		this.provinceName = provinceName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}

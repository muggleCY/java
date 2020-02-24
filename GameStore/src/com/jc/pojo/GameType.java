package com.jc.pojo;

import java.util.Date;


public class GameType {

	private Integer id;
	private String typeName;
	private Integer typeStatus;
	private String typePicture;
	private Date createTime;
	private Date updateTime;
	
	public GameType() {
	}

	public GameType(Integer id, String typeName, Integer typeStatus,
			String typePicture, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.typeStatus = typeStatus;
		this.typePicture = typePicture;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(Integer typeStatus) {
		this.typeStatus = typeStatus;
	}

	public String getTypePicture() {
		return typePicture;
	}

	public void setTypePicture(String typePicture) {
		this.typePicture = typePicture;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
}

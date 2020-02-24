package com.jc.pojo;
/**
 * 管理员
 * @author soft01
 *
 */
public class Manager {
	private Integer id; //编号
	private String username; //用户名
	private String password; //密码
	
	public Manager() {
	}

	public Manager(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
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
}

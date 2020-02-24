package com.web.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用package对一类action做分包整合，使用相同的命名空间在用户访问的时候做区分
 * @author soft01
 */
public class Package {
	/**
	 * 用来区分不同的package
	 */
	private String name;
	/**
	 * 用户访问的链接中在项目名和实际操作名之间的部分
	 * localhost://项目名/命名空间/项目名
	 */
	private String namespace;

	/**
	 * 在此包下含有的所有的action
	 */
	private Map<String, Action> actions = new HashMap<String, Action>();

	/**
	 * 获取package中存在的对象
	 * 根据名字获取
	 * @param name
	 * @return
	 */
	public Action getAction(String name) {
		return actions.get(name);
	}

	/**
	 *get.set方法
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public void setActions(Map<String, Action> actions) {
		this.actions = actions;
	}
	
	
}

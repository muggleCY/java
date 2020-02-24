package com.web.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * mvc的配置对象
 */
public class MVCConfig {
	/**
	 * 配置文件中出现的所有package对象
	 * @String 用户链接中出现的命名空间
	 * @Package 与命名空间对应的package对象
	 */
	private Map<String, Package> packages = new HashMap<String, Package>();
	/**
	 * 根据命名空间获取对应的package对象
	 * @param name
	 * @return
	 */
	public Package getPackage(String name) {
		return packages.get(name);
	}
	/**
	 * get.set方法
	 */
	public Map<String, Package> getPackages() {
		return packages;
	}

	public void setPackages(Map<String, Package > packages) {
		this.packages = packages;
	}
}

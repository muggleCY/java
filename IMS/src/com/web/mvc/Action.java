package com.web.mvc;
//<action name="login" class="com.web.action.UserAction" method="doLogin">
//	<result name="success">/qqqZoneIndex.jsp</result>
//	<result name="fail">/qqqZoneLogin.jsp</result>
//</action>

import java.util.HashMap;
import java.util.Map;

@lombok.Data
public class Action {
	/**
	 * 直接参与对链接的分配
	 */
	private String name;
	/**
	 * 链接交给哪个类处理
	 */
	private String id;
	/**
	 * 交由哪个类来处理
	 */
	private String method;

	/**
	 *请求完成后需要的跳转的页面
	 * @String 和result的name属性一一对应
	 */
	private Map<String, Result> results = new HashMap<String, Result>();

	public Result getResult(String name) {
		return results.get(name);
	}
	
}

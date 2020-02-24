package com.web.mvc;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * 从文件中获取数据，转化成MVCConfig对象并返回给调用者
 * @author soft01
 */
@SuppressWarnings("ALL")
public class MVCConfigParser {

	private static MVCConfig mvcConfig = new MVCConfig();

	static {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(MVCConfigParser.class.getClassLoader().getResourceAsStream("mvc.xml"));
			parse(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static MVCConfig getConfig() {
		return mvcConfig;
	}

	private static void parse(Document document) {
		//获取根元素（mvc）
		Element rootElement = document.getRootElement();
		//获取所有的package元素
		List<Element> packageElements = rootElement.elements("package");
		//依次生成所有的package对象
		for (Element packageElement : packageElements) {
			//获取所有属性值
			String namespace = packageElement.attributeValue("namespace");
			String packageName = packageElement.attributeValue("name");
			//创建package对象，并将属性赋值
			Package packageObj = new Package();
			packageObj.setName(packageName);
			packageObj.setNamespace(namespace);
			//获取当前package元素中所有的action元素
			List<Element> actionElements = packageElement.elements("action");
			//依次生成所有的action对象
			for (Element actionElement : actionElements) {
				//获取所有属性值
				String actionName = actionElement.attributeValue("name");
//				String className = actionElement.attributeValue("class");
				String id = actionElement.attributeValue("id");
				String method = actionElement.attributeValue("method");
				//创建action对象，并将属性赋值
				Action action = new Action();
				action.setId(id);
				action.setName(actionName);
//				action.setClassName(className);
				action.setMethod(method);
				//获取当前action元素中所有的result元素
				List<Element> resultElements = actionElement.elements("result");
				//依次生成所有的result对象
				for (Element resultElement : resultElements) {
					//获取所有属性
					String resultName = resultElement.attributeValue("name");
					String type = resultElement.attributeValue("type");
					String targetUrl = resultElement.getText();
					//创建result对象，并将属性赋值
					Result result = new Result();
					result.setName(resultName);
					result.setType(type);
					result.setTargetUrl(targetUrl);
					//向对应的action中装配result对象
					action.getResults().put(resultName, result);
				}
				//向对应的package中装配action对象
				packageObj.getActions().put(actionName, action);
			}
			//装配package对象
			mvcConfig.getPackages().put(namespace, packageObj);
		}
	}
}

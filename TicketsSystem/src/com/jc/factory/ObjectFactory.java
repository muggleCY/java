package com.jc.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
	static Map<String, Object> store = new HashMap<String, Object>();
	
	static{
		BufferedReader br = new BufferedReader(new InputStreamReader(
				ObjectFactory.class.getClassLoader().getResourceAsStream("object.properties")));
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] params = line.split("=");
					store.put(params[0], Class.forName(params[1]).newInstance());
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object getObject(String name){
		return store.get(name);
	}
}

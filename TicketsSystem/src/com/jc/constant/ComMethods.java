package com.jc.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComMethods {
	public static boolean matches(String regex,String in){
		Pattern p = Pattern.compile(regex);  //创建正则表达式对象
		Matcher m = p.matcher(in);			//匹配器
		boolean isMatch = m.matches();		//判断是否匹配
		return isMatch;
	}
}

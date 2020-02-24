package com.jc.util;

import java.util.Random;

public class CardUtil {
	public static String getRandom(Integer length){
		String codesStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";	
		String cardNum = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			cardNum = cardNum + codesStr.charAt(random.nextInt(62));
		}
		return cardNum;
	}
}

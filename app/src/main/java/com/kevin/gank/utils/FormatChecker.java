package com.kevin.gank.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

/**
 * 
 * 检查格式是否正确
 * 
 */
public class FormatChecker {
	
	/**
	 * 检查输入信息是否是邮箱
	 * @param strEmail 要检查的输入信息
	 * @return 邮箱格式是否正确
	 */
	public static boolean isEmail(String strEmail) {
		
		String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}
	
	/**
	 * 检查输入信息是否是手机
	 * @param phoneNumber 要检查的输入信息
	 * @return 手机格式是否正确
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		/** 可接受的电话格式: 
         * (123)456-78901, 123-456-78901, 12345678901, (123)-456-78901   
         */  
        String strPattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";  
        
        /** 可接受的电话格式2: 
         * (123)4567-8901, 123-4567-8901, 12345678901, (123)-4567-8901   
         */
        String strPattern2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";

        Pattern pattern = Pattern.compile(strPattern);
        Matcher m = pattern.matcher(phoneNumber);
        Pattern pattern2 = Pattern.compile(strPattern2);
        Matcher m2 = pattern2.matcher(phoneNumber);
        
        return (m.matches() || m2.matches());
	}
//	/**
//	 * 验证手机格式
//	 */
//	public static boolean isPhoneNumber2(String mobiles) {
//		/*
//		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
//		联通：130、131、132、152、155、156、185、186
//		电信：133、153、180、189、（1349卫通）
//		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
//		*/
//		String telRegex = "^[1][0123456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//		if (mobiles==null||mobiles.length()==0) return false;
//		else return mobiles.matches(telRegex);
//    }

	//暂定的判断规则为1开头，共11个数字为正确
	public static boolean isCellPhoneNumber( String cellPhoneNum ) {
		String strPattern = "^[1][0-9]{10}";
		Pattern pattern = Pattern.compile(strPattern);
        Matcher m = pattern.matcher(cellPhoneNum);
		return m.matches();
	}
	
	public static boolean isUsername(String username) {
		Log.i("username", username);
		String strPattern = "^[a-zA-Z][\\w]*$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(username);
		return m.matches();
	}

	public static boolean isWeizhuNum(String username) {
		String strPattern = "^[a-zA-Z][\\w-]{5,19}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	
	public static String getNumberFromString( String mString ) {
		String strPattern = "[^\\d]*";
		Pattern mPattern = Pattern.compile(strPattern);
		Matcher mMatcher = mPattern.matcher(mString);
		String rtnString = mMatcher.replaceAll("");
		return rtnString;
	}
}

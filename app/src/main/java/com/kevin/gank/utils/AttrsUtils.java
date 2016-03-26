package com.kevin.gank.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class AttrsUtils {
	static String android = "http://schemas.android.com/apk/res/android";
	static String myAttrs = "http://schemas.android.com/apk/res/com.weicheche.android";
	
	/**
	 * 获取对应属性的String文字值
	 * @param context
	 * @param attrs
	 * @param name
	 * @return
	 */
	public static String getStringFromSystemAttrs(Context context, AttributeSet attrs, String name){
		String hintId = attrs.getAttributeValue(android, name);
		if(hintId!=null&&hintId.length()>0&&!hintId.equals("null")){
			if(hintId.matches("@[0-9]*")){
				hintId = hintId.replace("@", "");
				String tem = context.getResources().getString(Integer.parseInt(hintId));
				return tem;
			}
		}
		return hintId;
	}
	
	/**
	 * 获取控件自定义（attrs文件里）的属性值，支持控件配置如："帮助"，"@string/help"
	 * @param context
	 * @param attrs
	 * @param name
	 * @return
	 */
	public static String getStringFromCustomAttrs(Context context, AttributeSet attrs, String name){
		String hintId = attrs.getAttributeValue(myAttrs, name);
		if(hintId!=null&&hintId.length()>0&&!hintId.equals("null")){
			if(hintId.matches("@[0-9]*")){
				hintId = hintId.replace("@", "");
				String tem = context.getResources().getString(Integer.parseInt(hintId));
				return tem;
			}
		}
		return hintId;
	}
	
	public static String getAttrs(Context context,AttributeSet attrs,String name){
		String Id = attrs.getAttributeValue(android, name);
		return Id;
	}
}

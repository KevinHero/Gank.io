package com.kevin.gank.utils;

public class NumUtil {
	 public static final String[] enNum = { // 鍩烘湰鏁拌瘝琛?
	 "zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
	   "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
	   "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
	   "twenty", "", "", "", "", "", "", "", "", "", "thirty", "", "", "",
	   "", "", "", "", "", "", "fourty", "", "", "", "", "", "", "", "",
	   "", "fifty", "", "", "", "", "", "", "", "", "", "sixty", "", "",
	   "", "", "", "", "", "", "", "seventy", "", "", "", "", "", "", "",
	   "", "", "eighty", "", "", "", "", "", "", "", "", "", "ninety" };

	 public static final String[] enUnit = { "hundred", "thousand", "million",
	   "billion", "trillion", "quintillion" }; //


	 public static String analyze(long num) { // long鍨嬪弬鏁帮紝
	  return analyze(String.valueOf(num)); // 鍥犱负long鍨嬫湁鏋侀檺锛屾墍浠ヤ互瀛楃涓插弬鏁版柟娉曚负涓?
	 }

	 public static String analyze(String num) { // 鏁板瓧瀛楃涓插弬鏁?
	  // 鍒ゆ柇瀛楃涓叉槸鍚︿负鏁板瓧
	  if (!num.matches("\\d+")) {
	   return String.format("%s is not number", num);
	  }

	  num = num.replaceAll("^[0]*([1-9]*)", "$1"); // 鎶婂瓧绗︿覆鍓嶉潰鐨?0鍘绘帀

	  if (num.length() == 0) { // 濡傛灉闀垮害涓?0锛屽垯鍘熶覆閮芥槸0
	   return enNum[0];
	  } else if (num.length() > 9) { // 濡傛灉澶т簬9锛屽嵆澶т簬999999999锛岄鐩檺鍒舵潯浠?
	   return "too big";
	  }

	  // 鎸?3浣嶅垎鍓插垎缁?
	  int count = (num.length() % 3 == 0) ? num.length() / 3
	    : num.length() / 3 + 1;
	  if (count > enUnit.length) {
	   return "too big";
	  } // 鍒ゆ柇缁勫崟浣嶆槸鍚﹁秴杩囷紝
	  // 鍙互鏍规嵁闇?姹傞?傚綋杩藉姞enUnit
	  String[] group = new String[count];
	  for (int i = num.length(), j = group.length - 1; i > 0; i -= 3) {
	   group[j--] = num.substring(Math.max(i - 3, 0), i);
	  }

	  StringBuilder buf = new StringBuilder(); // 缁撴灉淇濆瓨
	  for (int i = 0; i < count; i++) { // 閬嶅巻鍒嗗壊鐨勭粍
	   int v = Integer.valueOf(group[i]);
	   if (v >= 100) { // 鍥犱负鎸?3浣嶅垎鍓诧紝鎵?浠ヨ繖閲屼笉浼氭湁瓒呰繃999鐨勬暟
	    buf.append(enNum[v / 100]).append(" ").append(enUnit[0])
	      .append(" ");
	    v = v % 100; // 鑾峰彇鐧句綅锛屽苟寰楀埌鐧句綅浠ュ悗鐨勬暟
	    if (v != 0) {
	     buf.append("and ");
	    } // 濡傛灉鐧句綅鍚庣殑鏁颁笉涓?0锛屽垯杩藉姞and
	   }
	   if (v != 0) { // 鍓嶆彁鏄痸涓嶄负0鎵嶄綔瑙ｆ瀽
	    if (v < 20 || v % 10 == 0) { // 濡傛灉灏忎簬20鎴?10鐨勬暣鏁板?嶏紝鐩存帴鍙栧熀鏈暟璇嶈〃鐨勫崟璇?
	     buf.append(enNum[v]).append(" ");
	    } else { // 鍚﹀垯鍙?10浣嶆暟璇嶏紝鍐嶅彇涓綅鏁拌瘝
	     buf.append(enNum[v - v % 10]).append(" ");
	     buf.append(enNum[v % 10]).append(" ");
	    }
	    if (i != count - 1) { // 鐧句綅浠ヤ笂鐨勭粍杩藉姞鐩稿簲鐨勫崟浣?
	     buf.append(enUnit[count - 1 - i]).append(" ");
	    }
	   }
	  }

	  return buf.toString().trim(); // 杩斿洖鍊?
	 }
	}
package com.kevin.gank.utils;

import android.text.TextUtils;

public class JSONutil {

	public static final String removeBOM(String data) {
		if (TextUtils.isEmpty(data)) {
			return data;
		}

		if (data.startsWith("\ufeff")) {
			return data.substring(1);
		} else {
			return data;
		}
	}

	/*public static boolean isEquals(JSONObject object1, JSONObject object2) {
		if ((object1 == null && object2 != null)
				|| (object1 != null && object2 == null)
				|| object1.length() != object2.length()) {
			return false;
		}
		return true;
	}*/

    /**
     * 获取测试Json数据
     * @return 测试Json数据
     */
	public static String getTestJsonString() {
//		String jsonStr = "{\"data\":{\"default_type\":1,\"items\":[{\"type\":\"1\",\"img_selected_url\":\"fs.weicheche.cn\\/images\\/oilstations\\/146612\\/4.jpg\",\"img_normal_url\":\"fs.weicheche.cn\\/images\\/oilstations\\/146612\\/3.jpg\"},{\"type\":\"2\",\"img_selected_url\":\"fs.weicheche.cn\\/images\\/oilstations\\/146612\\/4.jpg\",\"img_normal_url\":\"fs.weicheche.cn\\/images\\/oilstations\\/146612\\/3.jpg\"}]},\"info\":\"OK\",\"status\":200}";
		String jsonStr = "{\"data\":{\"default_type\":1,\"items\":[]},\"info\":\"OK\",\"status\":200}";
		return jsonStr;
	}



}

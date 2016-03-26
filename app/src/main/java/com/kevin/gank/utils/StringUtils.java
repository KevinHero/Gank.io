package com.kevin.gank.utils;

import java.nio.charset.Charset;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StringUtils {

    /**
     * @param mString 参数应该为1990-09-09这种形式
     * @return
     * @throws
     * @Title: getAgeFromString
     * @Description: 根据字符串返回年龄
     * @date 2014年7月31日 上午3:54:25 creasylai
     */
    public static String getAgeFromString (String mString) {
        int times[] = {0, 0, 0};
        if (mString == null || mString.equals("")) {
            return "";
        }
        String temp[] = mString.split("-");
        if (temp.length != 3) {
            return "";
        }
        for (int i = 0; i < temp.length; i++) {
            times[i] = Integer.parseInt(temp[i]);
        }
        GregorianCalendar mGregorianCalendar = new GregorianCalendar();
        int yearNow = mGregorianCalendar.get(GregorianCalendar.YEAR);
        int monthNow = mGregorianCalendar.get(GregorianCalendar.MONTH);
        int dayOfMonthNow = mGregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        mGregorianCalendar = new GregorianCalendar(times[0], times[1], times[2]);
        int yearBorth = mGregorianCalendar.get(GregorianCalendar.YEAR);
        int monthBorth = mGregorianCalendar.get(GregorianCalendar.MONTH);
        int dayOfMonthBorth = mGregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int age = yearNow - yearBorth;
        if (monthBorth == (monthNow + 1)) {
            if (dayOfMonthBorth <= dayOfMonthNow) {
                //do nothing
            } else {
                age--;
            }
        } else {
            if (monthBorth < (monthNow + 1)) {
                //do nothing
            } else {
                age--;
            }
        }
        return age + "";
    }

    /***
     * @param text
     * @param target
     * @return
     * @Description remove the target sequence
     */
    public static String removeSubSequence (String text, String target) {
        while (text.contains(target)) {
            int idx = text.indexOf(target);
            text = text.substring(0, idx) + text.substring(idx + 1);
        }
        return text;
    }

    public static String formatDataSize (int size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format(Locale.getDefault(), "%dK", size / 1024);
        } else {
            ret = String.format(Locale.getDefault(), "%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }

    /**
     * 返回中间四位变为****的手机号码
     * 如：18888888888 -> 188****8888
     *
     * @param phoneNum
     * @return
     */
    public static String formatPhoneNum (String phoneNum) {
        StringBuilder rtnString = new StringBuilder();
        if (phoneNum != null && !phoneNum.equals("")) {
            rtnString.append(phoneNum.substring(0, 3));
            rtnString.append("****");
            rtnString.append(phoneNum.substring(7, phoneNum.length()));
        }
        return rtnString.toString();
    }

    public static String grouponPswWrapper (String psw) {
        String pswWrapper = "";
        if (12 == psw.length()) {
            pswWrapper = psw.substring(0, 4) + "  " + psw.substring(4, 8) + "  " + psw.substring(8,
                                                                                                 12);
        }
        return pswWrapper;
    }


    //-----------------给字符串加空格--------------开始

    /**
     * 例source = 1234567890
     * arr = new int[]{3,4,4}
     * 会被转成：123 4567 890
     *
     * @param source 源字符串，包含的空格会被去掉。
     * @param arr    格式数组，如new int[]{3,4,4}表将字符串1234567890分成123 4567 890
     * @param isLoop 是否循环
     * @return
     */
    public static String getFormatString (String source, int[] arr, boolean isLoop) {
        if (source == null || source.length() == 0 || arr == null || arr.length == 0) {
            return source;
        }
        String desc = "";
        String sourceNotSpace = source.replace(" ", "");
        if (arr != null) {
            desc = addSpace(desc, sourceNotSpace, arr, isLoop);
        }
        desc = desc.substring(1, desc.length());
        return desc;
    }

    private static String addSpace (String desc, String sourceNotSpace, int[] arr, boolean isloop) {
        String temp = new String(sourceNotSpace);
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i];
            if (temp.length() > index) {
                String temp2 = temp.substring(0, index);
                desc += " " + temp2;
                temp = temp.substring(index, temp.length());
            } else {
                desc += " " + temp;
                temp = "";
                break;
            }
            if (isloop == false && i == (arr.length - 1)) {
                desc += temp;
            }
        }
        if (isloop && temp.length() > 0) {
            desc = addSpace(desc, temp, arr, isloop);
        }
        return desc;
    }
    //-----------------给字符串加空格--------------结束

    public static boolean compareStrings (String first, String second) {
        if (first == second || (null != first && first.equals(second))) {
            return true;
        }
        boolean result = false;
        Charset cset = Charset.forName("UTF-8");
        String str1 = new String(first.getBytes(), cset);
        String str2 = new String(second.getBytes(), cset);
        result = str1.equals(str2);
        return result;
    }

    /**
     * 输入秒 返回时间的字符串
     * 如：
     * 1s      -->"1分钟"
     * 61s     -->"2分钟"
     * 3601s   -->"1小时1分钟"
     *
     * @param second_num
     * @return
     */
    public static String getTimeStrBySecondNum (int second_num) {
        String timestr = "";

        if (second_num / 3600 > 0) {
            timestr += (second_num / 3600) + "小时";
            if (0 == second_num % 3600) return timestr;
            second_num %= 3600;
        }

        if (0 == second_num % 60) timestr += second_num / 60 + "分钟";
        else timestr += (second_num / 60 + 1) + "分钟";

        return timestr;
    }
}

package com.kevin.gank.utils;

/**
 * view utils
 * 深圳喂车科技有限公司源代码，版权@归喂车车所有。
 * 项目: Gankcom.apanda.gank
 * 作者: 熊凯 on 16/4/28 18:47
 * 邮箱: kai.xiong@weicheche.cn
 */

import android.app.Activity;
import android.view.View;

public class V {

    /**
     * activity.findViewById()
     *
     * @param context
     * @param id
     * @return
     */
    public static <T extends View> T f(Activity context, int id) {
        return (T) context.findViewById(id);
    }

    /**
     * rootView.findViewById()
     *
     * @param rootView
     * @param id
     * @return
     */
    public static <T extends View> T f(View rootView, int id) {
        return (T) rootView.findViewById(id);
    }
}
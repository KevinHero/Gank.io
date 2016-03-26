package com.kevin.gank.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    public static void toastShort(Context context, String str) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void toastShort(Context context, int str) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String str) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void toastLong(Context context, int str) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}

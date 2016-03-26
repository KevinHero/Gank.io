package com.kevin.gank.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.kevin.gank.R;


public class DialogUtils {

    public static void showDialogPrompt(Context context, String content) {
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage(content)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }

    public static void showDialogPrompt(Context context, String title, String content) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }


    public static void showDialogPrompt(Context context, String title, String content, DialogInterface.OnClickListener positiveListener) {
        if (title == null || title.length() == 0) {
            title = "提示";
        }
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定", positiveListener)
                .create().show();
    }

    /**
     * 两个按钮
     *
     * @param context
     * @param title
     * @param content
     * @param positionStr
     * @param positiveListener
     */
    public static AlertDialog showDialogPrompt(Context context, String title, String content, String positionStr, DialogInterface.OnClickListener positiveListener) {
        if (title == null || title.length() == 0) {
            title = "提示";
        }


        final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(content)
                .setPositiveButton(positionStr, positiveListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        //   alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();

        return alertDialog;

    }

    /**
     * 两个按钮的额功能都可以定义的dialog
     *
     * @param context
     * @param title
     * @param content
     * @param positionStr
     * @param nagativeStr
     * @param positiveListener
     * @param negasitiveListener
     * @return
     */
    public static AlertDialog showDialogPrompt(Context context, String title,
                                               String content,
                                               String positionStr, String nagativeStr,
                                               DialogInterface.OnClickListener positiveListener,
                                               DialogInterface.OnClickListener negasitiveListener) {
        if (title == null || title.length() == 0) {
            title = "提示";
        }


        final AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(content)
                .setPositiveButton(positionStr, positiveListener)
                .setNegativeButton(nagativeStr, negasitiveListener)
                .create();
        alertDialog.show();


        return alertDialog;

    }


    /**
     * 设置底部出现的Diaog
     *
     * @param id      布局文件
     * @param context 上下文
     * @return dialog
     */
    @NonNull
    public static Dialog showAlert(int id, Context context) {
        final Dialog dialog = new Dialog(context, R.style.Dialog_FS_TAB);

        //////设置为true点击区域外消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        dialog.setContentView(id);

        //自定义dialog的属性
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT; // 宽度
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 高度
        //lp.alpha = 0.7f; // 透明度

        // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);
        //设置动画
        dialogWindow.setWindowAnimations(R.style.PopupAnimation);

        dialog.show();
        return dialog;
    }


//    <style name="Dialog_FS_TAB">
//    <item name="android:windowFullscreen">true</item>
//    <item name="android:windowNoTitle">true</item>
//    <item name="android:backgroundDimEnabled">true</item>
//    <!-- 模糊 -->
//    <item name="android:windowBackground">@color/white</item>
//    <!-- 自己想要的背景 -->
//    </style>
//


//    <!--popwindow的动画-->
//    <style name="PopupAnimation" mce_bogus="1"
//    parent="android:Animation">
//    <item name="android:windowEnterAnimation">@anim/anim_dialog_show</item>
//    <item name="android:windowExitAnimation">@anim/anim_dialog_hide</item>
//    </style>
//
//    <style name="PopupAnimation_up" mce_bogus="1"
//    parent="android:Animation">
//    <item name="android:windowEnterAnimation">@anim/anim_pop_show</item>
//    <item name="android:windowExitAnimation">@anim/anim_pop_hide</item>
//    </style>


    /**
     * 设置中间出现的Diaog
     *
     * @param id      布局文件
     * @param context 上下文
     * @return dialog
     */
    @NonNull
    public static Dialog showAlertMid(int id, Context context) {

        final Dialog dialog = new Dialog(context);
        //设置为true点击区域外消失
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(id);

        //自定义dialog的属性

        //自定义dialog的属性
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT; // 宽度
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 高度
        //lp.alpha = 0.7f; // 透明度

        dialog.show();
        return dialog;
    }


}

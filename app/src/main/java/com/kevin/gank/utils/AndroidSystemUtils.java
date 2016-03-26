package com.kevin.gank.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.kevin.gank.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.pm.PackageManager.GET_UNINSTALLED_PACKAGES;

public class AndroidSystemUtils {

    public static String getSdcardDir () {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return "";
    }

    public Bundle getMetaData (Context context) {
        Bundle metaData = null;
        try {
            metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                                                                      PackageManager.GET_META_DATA).metaData;
        } catch (NameNotFoundException e) {
        }
        return metaData;
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isLocationServiceOpen (final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    /**
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS (Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                               "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (CanceledException e) {
            e.printStackTrace();
        }
    }

    public static final void openGPSSetting (Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            intent.setAction(Settings.ACTION_SETTINGS);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    public static final void openDialog (Context mContext, String title, String message, String positiveBtn, String negativeBtn, DialogInterface.OnClickListener pOnClick, DialogInterface.OnClickListener nOnClick, boolean isCancelable) {
        new AlertDialog.Builder(mContext).setTitle(title).setMessage(message).setPositiveButton(
                positiveBtn,
                pOnClick).setNegativeButton(negativeBtn,
                                            nOnClick).setCancelable(isCancelable).create().show();
    }

    //返回Application中的Meta-data元素值
    public static String getMetaData (Context mContext, String key) {
        ApplicationInfo mApplicationInfo = null;
        String rtnString = "";
        try {
            mApplicationInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),
                                                                               PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (mApplicationInfo != null) {
            rtnString = mApplicationInfo.metaData.getString(key);
        }
        if (null == rtnString || "".equals(rtnString)) {
            rtnString = "" + mApplicationInfo.metaData.getInt(key);
        }
        if (null == rtnString || "".equals(rtnString)) {
            rtnString = "unknow";
        }
        return rtnString;
    }

    public static void addPhoneNumberMonitor (final Context mContext, final EditText mEditText) {
        mEditText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        String regex = "[0-9]{11,11}";
        final Pattern mPattern = Pattern.compile(regex);
        mEditText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter (CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && source.toString().contains(" ")) {
                    return source.toString().replaceAll(" ", "");
                }
                return source;
            }
        }});
        mEditText.addTextChangedListener(new android.text.TextWatcher() {

            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                Matcher mMatcher = mPattern.matcher(s);
                if (!mMatcher.matches()) {
                    mEditText.setTextColor(mContext.getResources().getColor(R.color.red_main));
                } else {
                    mEditText.setTextColor(mContext.getResources().getColor(R.color.transparent_semi_es));
                }
            }

            @Override
            public void afterTextChanged (Editable s) {
            }

        });
    }

    public static boolean judgePhoneNumber (final Context mContext, final EditText mEditText) {
        String regex = "[0-9]{11,11}";
        final Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher(mEditText.getText().toString());
        return mMatcher.matches();
    }


    /**
     * 弹出键盘
     *
     * @param v 获取焦点的view
     */
    public static void showInputBroad (View v) {
        try {
            if (v == null) return;
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹出键盘
     *
     * @param v 获取焦点的view
     */
    public static void hideInputBroad (View v) {
        try {
            if (v == null) return;
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideInputBroad (Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 获取设备的序列号
     *
     * @param context
     * @return 设备的序列号 String
     */
    public static String getDeviceId (Context context) {
        String deviceId = null;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != telephonyManager) {
            deviceId = telephonyManager.getDeviceId();
        }
        return deviceId;
    }

    public static void phoneDial (String phoneNumber, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void setClipboard (String text, Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    public static String getSystemInfo () {
        String phoneInfo = "Product: " + android.os.Build.PRODUCT;
        phoneInfo += ",CPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += ",TAGS: " + android.os.Build.TAGS;
        phoneInfo += ",VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ",MODEL: " + android.os.Build.MODEL;
        phoneInfo += ",SDK: " + android.os.Build.VERSION.SDK_INT;
        phoneInfo += ",VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ",DEVICE: " + android.os.Build.DEVICE;
        phoneInfo += ",DISPLAY: " + android.os.Build.DISPLAY;
        phoneInfo += ",BRAND: " + android.os.Build.BRAND;
        phoneInfo += ",BOARD: " + android.os.Build.BOARD;
        phoneInfo += ",FINGERPRINT: " + android.os.Build.FINGERPRINT;
        phoneInfo += ",ID: " + android.os.Build.ID;
        phoneInfo += ",MANUFACTURER: " + android.os.Build.MANUFACTURER;
        return phoneInfo;
    }

    /**
     * @return 手机型号
     */
    public static String getDeviceInfo () {
        return android.os.Build.DEVICE;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion (Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return context.getString(R.string.app_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return context.getString(R.string.can_not_find_version_name);
        }
    }

//    public static List<String> getInstallAppPackageNames (Context context) {
//        PackageManager pm = context.getPackageManager();
//        List<String> appPackageNames = new ArrayList<String>();
//        //获取手机中所有安装的应用集合
//        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(GET_UNINSTALLED_PACKAGES);
//        for (ApplicationInfo info : applicationInfos) {
//            String packageName = info.packageName;
//            appPackageNames.add(packageName);
//        }
//        return appPackageNames;
//    }

    /**
     * 获取当前栈顶的Activity对象
     * @return
     */
    public static Activity getCurrentActivity () {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

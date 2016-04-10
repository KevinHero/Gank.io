package com.kevin.gank.ui.base;

import android.app.Application;

import com.kevin.gank.utils.logger.Logger;

/**
 * Created by kevin on 16/4/8.
 */
public class BaseApplications extends Application {
    private static BaseApplications mInstance = null;

    public static BaseApplications getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
        Logger.init("kevin");


    }
}

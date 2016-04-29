package com.kevin.gank.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.WindowManager;

import com.kevin.gank.R;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.bean.HisBean;
import com.kevin.gank.cons.Const;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.utils.DateTime;
import com.kevin.gank.utils.GsonUtils;
import com.kevin.gank.utils.SharePreferencesUilts;
import com.kevin.gank.utils.logger.Logger;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        String today = SharePreferencesUilts.readPreferences(this, "today", "");

        String dateString = DateTime.getDateString();
        if (today.equals(dateString)) {
            String json = SharePreferencesUilts.readPreferences(this, "json", "");
            jump2Main(json);
        } else {
            startNewThreadRequestData("http://gank.io/api/day/history", Const.REQUEST_HISTOTY_GANK_SUCCEED);
        }


        init();

    }

    @Override
    protected void refresh(Message msg) {
        String s = msg.obj.toString();

        switch (msg.what) {
            case Const.REQUEST_HISTOTY_GANK_SUCCEED:

                HisBean hisBean = GsonUtils.parserJsonToArrayBean(msg.obj.toString(), HisBean.class);
                String replace = hisBean.results.get(0).replace("-", "/");
                startNewThreadRequestData("http://gank.io/api/day/" + replace,
                        Const.REQUEST_DAY_GANK_SUCCEED);
                break;
            case Const.REQUEST_DAY_GANK_SUCCEED:
                Logger.json(s);
                SharePreferencesUilts.savePreferences(this, "today", DateTime.getDateString(), "json", s);
                jump2Main(s);
                break;
        }
    }

    private void jump2Main(String json) {
        final DayBean dayBean = GsonUtils.parserJsonToArrayBean(json, DayBean.class);

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainActivity.startActivity(SplashActivity.this, dayBean);
        this.finish();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void init() {


    }
}

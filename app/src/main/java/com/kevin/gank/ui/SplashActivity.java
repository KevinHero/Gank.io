package com.kevin.gank.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.kevin.gank.R;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.bean.HisBean;
import com.kevin.gank.protocol.NetUtils;
import com.kevin.gank.utils.DateTime;
import com.kevin.gank.utils.GsonUtils;
import com.kevin.gank.utils.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SplashActivity extends AppCompatActivity {


    private DayBean mDayBean = new DayBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);


        Logger.init("kevin_gank");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Thread() {

            @Override
            public void run() {
                super.run();


                try {


//                    http://gank.io/api/day/history

                    String history = NetUtils.run("http://gank.io/api/day/history");

                    Logger.json(history);

                    HisBean hisBean = GsonUtils.parserJsonToArrayBean(history, HisBean.class);

                    String run = null;
                    for (int i = 0; i < hisBean.results.size(); i++) {
                        String replace = hisBean.results.get(i).replace("-", "/");
                        run = NetUtils.run("http://gank.io/api/day/" + replace);
                        if (run.startsWith("{") && run.endsWith("}")) {
                            break;
                        }
                    }

//                    final String run = NetUtils.run("http://gank.io/api/day/2016/01/15");
                    Logger.json(run);

                    final DayBean dayBean = GsonUtils.parserJsonToArrayBean(run, DayBean.class);
//                    mDayBean = DayBean.parseJson(run);

                    sleep(1000);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.startActivity(SplashActivity.this, dayBean);

//                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }


}

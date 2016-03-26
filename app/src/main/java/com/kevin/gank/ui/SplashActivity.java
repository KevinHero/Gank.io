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
import com.kevin.gank.protocol.NetUtils;
import com.kevin.gank.utils.DateTime;
import com.kevin.gank.utils.GsonUtils;

import java.io.IOException;
import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {


    private DayBean mDayBean = new DayBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Thread() {

            @Override
            public void run() {
                super.run();


                try {

                    final String run = NetUtils.getTodayData();
//                    Log.d("SplashActivity", "onCreate: " + run);


                    final DayBean dayBean = GsonUtils.parserJsonToArrayBean(run, DayBean.class);
//                    mDayBean = DayBean.parseJson(run);

                    sleep(1000);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.startActivity(SplashActivity.this, dayBean);
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

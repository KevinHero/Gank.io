package com.kevin.gank.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kevin.gank.R;
import com.kevin.gank.ui.customview.ZProgressHUD;
import com.kevin.gank.utils.HttpDownloader;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kai.xiong on 2016/3/27.
 */
public abstract class BaseActivity extends AppCompatActivity {


    private ZProgressHUD  mProgressHUD ;
    protected Context mContext;
    protected String TAG;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressHUD = ZProgressHUD.getInstance(this);
        mContext = this;
        showLoadingAnimation();
        //定义一个线程池 具有两个线程的线程池
        executorService = Executors.newFixedThreadPool(2);
        TAG = this.toString();
        init();
        initView();
    }

    protected void showLoadingAnimation() {

        mProgressHUD.show();
    }

    protected final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            dissmissDialog();
            refresh(msg);
        }

    };


    protected abstract void refresh(Message msg);


    private ExecutorService executorService;



    /**
     * 开启新线程请求班结数据网络
     */
    protected void startNewThreadRequestData(final String url, final int REQUESTCODE) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String json = null;
                try {
                    json = HttpDownloader.getInstance().run(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = REQUESTCODE;
                msg.obj = json;
                msg.sendToTarget();

            }
        });
    }



    protected void dissmissDialog() {
        mProgressHUD.dismiss();
    }


    protected void showLoadSuccessDialog() {
        mProgressHUD.dismissWithSuccess();
    }


    protected void showloadFailedDialog() {
        mProgressHUD.dismissWithFailure();
    }

    protected abstract void initView();

    protected abstract void init();
}

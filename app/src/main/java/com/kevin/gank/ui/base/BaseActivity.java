package com.kevin.gank.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kevin.gank.R;
import com.kevin.gank.ui.customview.ZProgressHUD;

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
        TAG = this.toString();
        init();
        initView();
    }

    protected void showLoadingAnimation() {

        mProgressHUD.show();
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

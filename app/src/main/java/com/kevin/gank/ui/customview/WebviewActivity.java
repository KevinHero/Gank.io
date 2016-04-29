package com.kevin.gank.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kevin.gank.R;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.utils.ToastUtils;

public class WebviewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;


    public static void startActivity(Context mContext, String data, String title) {
        Intent mIntent = new Intent(mContext, WebviewActivity.class);

        mIntent.putExtra("data", data);
        mIntent.putExtra("title", title);
        mContext.startActivity(mIntent);
    }


    @Override
    protected void refresh(Message msg) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void init() {

        showLoadingAnimation();
        final String data = getIntent().getStringExtra("data");
        final String title = getIntent().getStringExtra("title");
        initView(title);

        initWebview(data);
    }

    private void initWebview(String data) {

        webView = (WebView) findViewById(R.id.wv);


        //        tv_webview_title.setText(title);
        WebSettings webSettings = webView.getSettings();
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
        webSettings.setUseWideViewPort(true);
        //设置默认加载的可视范围是大视野范围
        webSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求,不用借助

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showLoadSuccessDialog();
                super.onPageFinished(view, url);
            }
        });


        webView.loadUrl(data);


    }

    private boolean ischecked;

    private void initView(String title) {

        setContentView(R.layout.activity_webview);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        //    toolbar.setLeft(R.drawable.star);

//        toolbar.setNavigationIcon( );
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.action_star:
                        //TODO 收藏


                        if (ischecked) {
                            ToastUtils.toastShort(WebviewActivity.this, "取消收藏");

                            item.setIcon(R.drawable.ic_star_outline_white);
                            ischecked = false;
                        } else {
                            ToastUtils.toastShort(WebviewActivity.this, "已收藏,请在收藏中查看!");

                            item.setIcon(R.drawable.ic_star_white);
                            ischecked = true;
                        }


                        break;


                }


                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_left_first:
//                onBackPressed();
//                break;
        }
    }

    /**
     * 保证，在按系统返回键的时候，不是退出程序，而是，返回上一个页面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.goBack(); //返回上一个页面
            return true;
        }
        return false;
    }

}


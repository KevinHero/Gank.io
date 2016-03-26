package com.kevin.gank.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.kevin.gank.R;
import com.kevin.gank.adapter.BeautyAdater;
import com.kevin.gank.adapter.HuaGirlsAdater;
import com.kevin.gank.bean.HuaGirlsBean;
import com.kevin.gank.bean.NewListBean;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.utils.GsonUtils;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;

public class BeautyActivity extends BaseActivity {


    private Toolbar toolbar;
    private ListView lv_hs_today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        initView();
        requestGirls();
    }


    private void requestGirls() {
        new Thread() {
            @Override
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/197-1", "13550", "8097738f0cc84ccfa4ba16426b019a85")

                        .addTextPara("num", "20")
                        .addTextPara("page", "1")
                        .post();
                System.out.println(res);

                final List<NewListBean> newslist = GsonUtils.parserJsonToArrayBeans(GsonUtils.getNoteJsonString(res, "showapi_res_body"), "newslist", NewListBean.class);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        lv_hs_today.setAdapter(new BeautyAdater(mContext, newslist));

                        dissmissDialog();
                    }
                });

            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.huaban, menu);
        return false;
    }

    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_hs_today = (ListView) findViewById(R.id.lv_hs_today);
        toolbar.setTitle("美女");

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


                return true;
            }
        });


    }

    @Override
    protected void init() {

    }


}

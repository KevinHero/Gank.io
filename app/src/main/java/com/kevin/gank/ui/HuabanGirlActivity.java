package com.kevin.gank.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.kevin.gank.R;
import com.kevin.gank.adapter.HuaGirlsAdater;
import com.kevin.gank.bean.HuaGirlsBean;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.ui.customview.NestedListView;
import com.kevin.gank.ui.customview.WebviewActivity;
import com.kevin.gank.utils.GsonUtils;
import com.kevin.gank.utils.ToastUtils;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;


/**
 * type	String	默认调用最新	34	否
 * 大胸妹=34
 * 小清新=35
 * 文艺范=36
 * 性感妹=37
 * 大长腿=38
 * 黑丝袜=39
 * 小翘臀=40
 * <p/>
 * num	String	20	10	否	默认20,最大50
 * page	String	1	1	否	查询第几页
 */
public class HuabanGirlActivity extends BaseActivity {

    private Toolbar toolbar;
    private ListView lv_hs_today;
    private int TYPE;
    private List<HuaGirlsBean> mBeanList = new ArrayList<HuaGirlsBean>();
    private HuaGirlsAdater mAdapter;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huaban_girl);
        showLoadingAnimation();
        TYPE = 34;
        initView();
        requestGirls(TYPE);
    }

    private void requestGirls(final int TYPE) {
        new Thread() {
            @Override
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/819-1", "13550", "8097738f0cc84ccfa4ba16426b019a85")
                        .addTextPara("type", String.valueOf(TYPE))
                        .addTextPara("num", "10")
                        .addTextPara("page", "1")
                        .post();
                System.out.println(res);

                final String noteJsonString = GsonUtils.getNoteJsonString(res, "showapi_res_body");
                mBeanList.clear();
                for (int i = 0; i < 10; i++) {
                    HuaGirlsBean huaGirlsBean = GsonUtils.parserJsonToArrayBean(noteJsonString, String.valueOf(i), HuaGirlsBean.class);
                    mBeanList.add(huaGirlsBean);
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new HuaGirlsAdater(HuabanGirlActivity.this, mBeanList);
                        lv_hs_today.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        dissmissDialog();
                    }
                });

            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.huaban, menu);
        return true;
    }

    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv_hs_today = (ListView) findViewById(R.id.lv_hs_today);
        toolbar.setTitle("花瓣美女" );

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
                    case R.id.action_34:
                        requestGirls(34);
                        toolbar.setTitle("花瓣美女--" + returnType(34));
                        break;
                    case R.id.action_35:
                        requestGirls(35);
                        toolbar.setTitle("花瓣美女--" + returnType(35));
                        break;
                    case R.id.action_36s:
                        requestGirls(36);
                        toolbar.setTitle("花瓣美女--" + returnType(36));
                        break;
                    case R.id.action_37:
                        requestGirls(37);
                        toolbar.setTitle("花瓣美女--" + returnType(37));
                        break;
                    case R.id.action_38:
                        requestGirls(38);
                        toolbar.setTitle("花瓣美女--" + returnType(38));
                        break;
                    case R.id.action_39:
                        requestGirls(39);
                        toolbar.setTitle("花瓣美女--" + returnType(39));
                        break;
                    case R.id.action_40:
                        requestGirls(40);
                        toolbar.setTitle("花瓣美女--" + returnType(40));
                        break;
                }
                return true;
            }
        });


    }

    @Override
    protected void init() {

    }


    private String returnType(int type) {
        String name = "";
        switch (type) {

            case 34:
                name = "大胸妹";
                break;
            case 35:
                name = "小清新";
                break;
            case 36:
                name = "文艺范";
                break;
            case 37:
                name = "性感妹";
                break;
            case 38:
                name = "大长腿";
                break;
            case 39:
                name = "黑丝袜";
                break;
            case 40:
                name = "小翘臀";
                break;
        }
        return name;
    }
}


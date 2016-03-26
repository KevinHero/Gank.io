package com.kevin.gank.ui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.kevin.gank.R;
import com.kevin.gank.adapter.ListViewMainAdapter;
import com.kevin.gank.adapter.ViewPagerAdapter;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.bean.ItemBean;
import com.kevin.gank.protocol.NetUtils;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.ui.customview.ZProgressHUD;
import com.kevin.gank.utils.GsonUtils;
import com.kevin.gank.utils.ToastUtils;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = "MainActivity";
    private DayBean mDayBean;
    private List<ImageView> imageViewContainer = new ArrayList<>();
    private String mStringExtra;
    private List<ItemBean> mBitmapBeans;
    private BoomMenuButton boomMenuButton;

    public static void startActivity(Context mContext, DayBean dayBean) {
        Intent mIntent = new Intent(mContext, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dayBean", dayBean);
        mIntent.putExtras(bundle);
        mContext.startActivity(mIntent);
    }

    public static void startActivity(Context mContext, String data) {
        Intent mIntent = new Intent(mContext, MainActivity.class);

        mIntent.putExtra("data", data);

        mContext.startActivity(mIntent);
    }


    @Override
    protected void init() {

        mDayBean = (DayBean) getIntent().getSerializableExtra("dayBean");
        Log.d("MainActivity", "init: " + mDayBean.toString());


        //请求图片

        new Thread() {
            @Override
            public void run() {
                try {
                    final String bitmapJson = NetUtils.run("http://gank.io/api/data/福利/20/1");
                    mBitmapBeans = GsonUtils.parserJsonToArrayBeans(bitmapJson, "results", ItemBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initViewPager(mBitmapBeans);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        //.mStringExtra = getIntent().getStringExtra("data");


    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        showLoadingAnimation();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("每日精选妹纸   " );
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//
//            }
//        });

//
        boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);
        boomMenuButton.setCancelable(true);
        boomMenuButton.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
            @Override
            public void onClick(int buttonIndex) {

                switch (buttonIndex) {
                    case 0:

                        startActivity(new Intent(mContext, HuabanGirlActivity.class));

                        break;
                    case 1:
                        startActivity(new Intent(mContext, HistoryTodayActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, BeautyActivity.class));
                        break;
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        initViewPager();
        ListView listView = (ListView) findViewById(R.id.lv_main);
////
////        listView.setAdapter(new NormalRecyclerViewAdapter(mDayBean));
        listView.setAdapter(new ListViewMainAdapter(this, mDayBean, false));


    }

    private boolean init = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.mipmap.huagirl,
                R.mipmap.histotry,
                R.mipmap.beauty

        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"BoomMenuButton", "View source code", "Follow me"};

        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(this, R.color.colorPrimary);
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }

        boomMenuButton.init(
                subButtonDrawables, // The drawables of images of sub buttons. Can not be null.
                null,     // The texts of sub buttons, ok to be null.
                subButtonColors,    // The colors of sub buttons, including pressed-state and normal-state.
                ButtonType.CIRCLE,     // The button type.
//                ButtonType.HAM,     // The button type.
                BoomType.PARABOLA,  // The boom type.
                PlaceType.CIRCLE_3_3,  // The place type.
                null,               // Ease type to move the sub buttons when showing.
                null,               // Ease type to scale the sub buttons when showing.
                null,               // Ease type to rotate the sub buttons when showing.
                null,               // Ease type to move the sub buttons when dismissing.
                null,               // Ease type to scale the sub buttons when dismissing.
                null,               // Ease type to rotate the sub buttons when dismissing.
                null                // Rotation degree.
        );

        boomMenuButton.setTextViewColor(ContextCompat.getColor(this, R.color.black));

    }


    private void initViewPager(List<ItemBean> bitmapBeans) {

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_title);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, bitmapBeans);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);


        dissmissDialog();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            HistoryListActivity.startActivity(MainActivity.this, "福利", "休息视频", 20, 1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        final int size = mDayBean.category.size();
        for (int i = 0; i < size; i++) {


        }

        if (id == R.id.one) {
//            setIntent("Android");

            HistoryListActivity.startActivity(MainActivity.this, "福利", "Android", 20, 0);
        } else if (id == R.id.two) {
//            setIntent("IOS");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "IOS", 20, 0);
        } else if (id == R.id.three) {
//            setIntent("休息视频");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "休息视频", 20, 0);
        } else if (id == R.id.four) {
//            setIntent("福利");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "福利", 20, 0);
        } else if (id == R.id.five) {
//            setIntent("拓展资源");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "拓展资源", 20, 0);
        } else if (id == R.id.six) {
//            setIntent("前端");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "前端", 20, 0);
        } else if (id == R.id.seven) {
//            setIntent("App");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "App", 20, 0);
        } else if (id == R.id.eight) {
//            setIntent("瞎推荐");
            HistoryListActivity.startActivity(MainActivity.this, "福利", "瞎推荐", 20, 0);
        } else if (id == R.id.nine) {

//            setIntent("随便看看");
//            HistoryListActivity.startActivity(MainActivity.this, "福利", "随便看看", 20, 0);
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 跳转到对于的分类页面
     */
    private void setIntent(String type) {
        try {
            NetUtils.category(type, this, CategoryActivity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

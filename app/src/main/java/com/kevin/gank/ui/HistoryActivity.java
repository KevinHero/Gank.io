package com.kevin.gank.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.kevin.gank.R;
import com.kevin.gank.adapter.ListViewMainAdapter;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.cons.Const;
import com.kevin.gank.ui.base.BaseActivity;

public class HistoryActivity extends BaseActivity {

    private DayBean mDayBean;
    private ListView lv_history;
    private Context mContext;

    @Override
    protected void init() {

        mContext = this;
        mDayBean = (DayBean) getIntent().getSerializableExtra("dayBean");

    }

    public static void startActivity(Context context, DayBean dayBean) {
        Intent mIntent = new Intent(context, HistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dayBean", dayBean);
        mIntent.putExtras(bundle);
        context.startActivity(mIntent);
    }

    @Override
    protected void refresh(Message msg) {

    }

    protected void initView() {
        setContentView(R.layout.activity_history);
        showLoadingAnimation();
        lv_history = (ListView) findViewById(R.id.lv_history);


        Const.isHistory = true;
        lv_history.setAdapter(new ListViewMainAdapter(mContext, mDayBean, true));

        showLoadSuccessDialog();
    }


}

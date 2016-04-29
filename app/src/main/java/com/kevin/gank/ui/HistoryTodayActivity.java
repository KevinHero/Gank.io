package com.kevin.gank.ui;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.gank.R;
import com.kevin.gank.bean.HistoryTodayBean;
import com.kevin.gank.ui.base.BaseActivity;
import com.kevin.gank.ui.customview.NestedListView;
import com.kevin.gank.ui.customview.WebviewActivity;
import com.kevin.gank.utils.DateTime;
import com.kevin.gank.utils.DialogUtils;
import com.kevin.gank.utils.GsonUtils;
import com.kevin.gank.utils.L;
import com.kevin.gank.utils.ToastUtils;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryTodayActivity extends BaseActivity {


    private NestedListView lv_hs_today;

    @Override
    protected void refresh(Message msg) {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_history_today);

        lv_hs_today = (NestedListView) findViewById(R.id.lv_hs_today);

        String dateString = DateTime.getDateString();
        requestdata(dateString.replace("/", "").substring(4));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setTitle("历史上的今天");
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
                Calendar calendar = Calendar.getInstance();
                String dateString;
                switch (item.getItemId()) {
                    case R.id.his_today_up:
                        //TODO 收藏

                        dateString = DateTime.formatTime(DateTime.beforeNDays(-1)).split(" ")[0].replace("-", "").substring(4);
                        requestdata(dateString);
                        break;
                    case R.id.his_today_choose:
                        //TODO 收藏
                        DatePickerDialog pickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                final String s = "0" + (monthOfYear + 1) + "" + (dayOfMonth <= 9 ? "0" + dayOfMonth : dayOfMonth);
                                requestdata(s.length() == 5 ? s.substring(1) : s);

                            }
                        }, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                        pickerDialog.show();

                        break;
                    case R.id.his_today_down:
                        //TODO 收藏
                        dateString = DateTime.formatTime(DateTime.beforeNDays(1)).split(" ")[0].replace("-", "").substring(4);
                        requestdata(dateString);

                        break;


                }


                return true;
            }
        });

    }

    private void requestdata(final String data) {
        new Thread() {
            @Override
            public void run() {

                String res = new ShowApiRequest("http://route.showapi.com/119-42", "13550", "8097738f0cc84ccfa4ba16426b019a85")
                        .addTextPara("date", data)
                        .post();
                L.d(TAG, res);

                final String showapi_res_body = GsonUtils.getNoteJsonString(res, "showapi_res_body");
                final String body = GsonUtils.getNoteJsonString(showapi_res_body, "list");

                final List<HistoryTodayBean> list = GsonUtils.parserJsonToArrayBeans(showapi_res_body, "list", HistoryTodayBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lv_hs_today.setAdapter(new HisTodayAdapter(list));
                    }
                });
            }
        }.start();
    }

    @Override
    protected void init() {

        showLoadingAnimation();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }


    private class HisTodayAdapter extends BaseAdapter {

        List<HistoryTodayBean> list = new ArrayList<>();

        public HisTodayAdapter(List<HistoryTodayBean> list) {
            dissmissDialog();
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public HistoryTodayBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(mContext, R.layout.ios_cardview, null);

            ViewHolder viewHolder = new ViewHolder(convertView);

            final HistoryTodayBean todayBean = getItem(position);

            viewHolder.tv_title.setText(todayBean.title);
            if (todayBean.img == null) {
//                viewHolder.iv_title.setVisibility(View.INVISIBLE);
//                Glide.with(mContext).load("http://ww1.sinaimg.cn/mw690/67eaffd3gw1e9mqvlkcl9g2046046mz9.gif").centerCrop().into(viewHolder.iv_title);
            } else {
                Glide.with(mContext).load(todayBean.img).centerCrop().into(viewHolder.iv_title);

            }

            viewHolder.tv_date.setText(todayBean.year + "/" + todayBean.month + "/" + todayBean.day);


            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public boolean onLongClick(View v) {
                    final Dialog dialog = DialogUtils.showAlert(R.layout.imageview, mContext);
                    final ImageView viewById = (ImageView) dialog.findViewById(R.id.iv_pic);
                    final TextView name = (TextView) dialog.findViewById(R.id.tv_name);
                    final TextView time = (TextView) dialog.findViewById(R.id.tv_time);


                    if (todayBean.img == null) {
//                viewHolder.iv_title.setVisibility(View.INVISIBLE);
                        Glide.with(mContext).load("http://ww4.sinaimg.cn/mw690/67eaffd3gw1dsrx9qjv4yg.gif").centerCrop().into(viewById);
                    } else {
                        Glide.with(mContext).load(todayBean.img).fitCenter().into(viewById);

                    }


                    Glide.with(mContext).load(todayBean.img).fitCenter().into(viewById);
                    name.setText(todayBean.title);
                    name.setTextColor(getColor(R.color.colorPrimary));
                    time.setTextColor(getColor(R.color.colorPrimary));
                    time.setText(todayBean.year + "/" + todayBean.month + "/" + todayBean.day);

                    return true;
                }
            });


            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public ImageView iv_title;
            public TextView tv_title;
            public TextView tv_date;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.iv_title = (ImageView) rootView.findViewById(R.id.iv_title);
                this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
                this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            }

        }
    }
}

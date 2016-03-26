package com.kevin.gank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.gank.R;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.bean.ItemBean;
import com.kevin.gank.cons.Const;
import com.kevin.gank.ui.customview.NestedListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai.xiong on 2016/3/26.
 */
public class ListViewMainAdapter extends BaseAdapter {

    DayBean dayBean;
    private Context mContext;
    List list = new ArrayList();
    boolean isHis;

    public ListViewMainAdapter(Context context, DayBean dayBean, boolean isHistory) {
        this.dayBean = dayBean;
        isHis = isHistory;
        mContext = context;


        if (isHis) {
            if (dayBean.results.福利 != null) list.add(dayBean.results.福利);
        }

        if (dayBean.results.Android != null) list.add(dayBean.results.Android);

        if (dayBean.results.App != null) list.add(dayBean.results.App);

        if (dayBean.results.iOS != null) list.add(dayBean.results.iOS);

        if (dayBean.results.休息视频 != null) list.add(dayBean.results.休息视频);

        if (dayBean.results.前端 != null) list.add(dayBean.results.前端);

        if (dayBean.results.拓展资源 != null) list.add(dayBean.results.拓展资源);

        if (dayBean.results.瞎推荐 != null) list.add(dayBean.results.瞎推荐);


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final List<ItemBean> itemBeans = (List<ItemBean>) list.get(position);


        convertView = View.inflate(mContext, R.layout.android_cardview, null);

        NestedListView nestedListView = (NestedListView) convertView.findViewById(R.id.nestedlistview);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_android_carview);
        TextView tv_name_main = (TextView) convertView.findViewById(R.id.tv_name_main);
        TextView tv_time_main = (TextView) convertView.findViewById(R.id.tv_time_main);
        RelativeLayout rl_main = (RelativeLayout) convertView.findViewById(R.id.rl_main);
        ImageView iv_title_main = (ImageView) convertView.findViewById(R.id.iv_title_main);


        if (itemBeans.get(0).type.equals("福利")) {
            nestedListView.setVisibility(View.GONE);
            rl_main.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(itemBeans.get(0).url).centerCrop().into(iv_title_main);
            tv_time_main.setText(itemBeans.get(0).createdAt.split("T")[0]);
            tv_name_main.setText(itemBeans.get(0).who);

        } else {
            rl_main.setVisibility(View.GONE);
            nestedListView.setAdapter(new ListViewAdapter(mContext, itemBeans));
        }
        textView.setText(itemBeans.get(0).type);

        return convertView;

    }
}

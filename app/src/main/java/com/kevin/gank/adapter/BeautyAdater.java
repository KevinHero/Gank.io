package com.kevin.gank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.gank.R;
import com.kevin.gank.bean.NewListBean;
import com.kevin.gank.ui.customview.WebviewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai.xiong on 2016/3/27.
 */
public class BeautyAdater extends BaseAdapter {

    List<NewListBean> list = new ArrayList<>();
    Context mContext;

    public BeautyAdater(Context context, List<NewListBean> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(mContext, R.layout.beautygirls_cardview, null);

        ViewHolder viewHolder = new ViewHolder(convertView);

        final NewListBean todayBean = getItem(position);

        viewHolder.tv_title.setText(todayBean.title);


        if (todayBean.picUrl == null) {
//                viewHolder.iv_title.setVisibility(View.INVISIBLE);
            Glide.with(mContext).load("http://ww1.sinaimg.cn/mw690/67eaffd3gw1e9mqvlkcl9g2046046mz9.gif").centerCrop().into(viewHolder.iv_title);
        } else {
            Glide.with(mContext).load(todayBean.picUrl).centerCrop().into(viewHolder.iv_title);

        }


        viewHolder.tv_title_desc.setText(todayBean.description);
        viewHolder.tv_time_beauty.setText(todayBean.ctime);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebviewActivity.startActivity(mContext, todayBean.url, todayBean.title);
            }
        });


        return convertView;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_title_desc;
        public ImageView iv_title;
        public TextView tv_time_beauty;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_title_desc = (TextView) rootView.findViewById(R.id.tv_title_desc);
            this.iv_title = (ImageView) rootView.findViewById(R.id.iv_title);
            this.tv_time_beauty = (TextView) rootView.findViewById(R.id.tv_time_beauty);
        }

    }
}

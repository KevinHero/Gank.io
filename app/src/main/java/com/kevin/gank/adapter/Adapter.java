package com.kevin.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kevin.gank.R;
import com.kevin.gank.bean.DayBean;
import com.kevin.gank.bean.ItemBean;
import com.kevin.gank.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai.xiong on 2016/3/25.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.NormalTextViewHolder> {

    DayBean dayBean;
    List list = new ArrayList();

    public Adapter(DayBean dayBean) {
        this.dayBean = dayBean;

        if (dayBean.results.Android != null) list.add(dayBean.results.Android);

        if (dayBean.results.App != null) list.add(dayBean.results.App);

        if (dayBean.results.iOS != null) list.add(dayBean.results.iOS);

        if (dayBean.results.休息视频 != null) list.add(dayBean.results.休息视频);

        if (dayBean.results.前端 != null) list.add(dayBean.results.前端);

        if (dayBean.results.拓展资源 != null) list.add(dayBean.results.拓展资源);

        if (dayBean.results.瞎推荐 != null) list.add(dayBean.results.瞎推荐);

        if (dayBean.results.福利 != null) list.add(dayBean.results.福利);


    }


    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;//new NormalTextViewHolder(View.inflate(MainActivity.this, R.layout.listview_cardview, null));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {

        final List<ItemBean> itemBean = (List<ItemBean>) list.get(position);


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder {


        NormalTextViewHolder(View view) {
            super(view);


            ImageView imageView = (ImageView) view.findViewById(R.id.iv_title);

//                Glide.with(MainActivity.this).load(mDayBean.results.福利.get(0).url).centerCrop().into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}


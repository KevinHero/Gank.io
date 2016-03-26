package com.kevin.gank.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.gank.R;
import com.kevin.gank.bean.ItemBean;
import com.kevin.gank.ui.MainActivity;
import com.kevin.gank.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai.xiong on 2016/3/25.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> imageViewContainer = new ArrayList<>();
    List<ItemBean> bitmapBeans;
    private Context context;

    public ViewPagerAdapter(Context context, List<ImageView> imageViewContainer) {
        this.imageViewContainer = imageViewContainer;
        this.context = context;
    }

    public ViewPagerAdapter(MainActivity context, List<ItemBean> bitmapBeans) {
        this.bitmapBeans = bitmapBeans;

        for (int i = 0; i < bitmapBeans.size(); i++) {

            ImageView imageView = new ImageView(context);
            Glide.with(context).load(bitmapBeans.get(i).url).centerCrop().into(imageView);
            imageViewContainer.add(imageView);
        }

        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewContainer.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        View view = View.inflate(context, R.layout.imageview, null);


        final TextView name = (TextView) view.findViewById(R.id.tv_name);
        final TextView time = (TextView) view.findViewById(R.id.tv_time);
        final ImageView iv_pic = (ImageView) view.findViewById(R.id.iv_pic);

        Glide.with(context).load(bitmapBeans.get(position).url).centerCrop().into(iv_pic);
        name.setText(bitmapBeans.get(position).who);
        time.setText(bitmapBeans.get(position).publishedAt);
        // 为每一个page添加点击事件
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = DialogUtils.showAlert(R.layout.imageview, context);
                final ImageView viewById = (ImageView) dialog.findViewById(R.id.iv_pic);
                final TextView name = (TextView) dialog.findViewById(R.id.tv_name);
                final TextView time = (TextView) dialog.findViewById(R.id.tv_time);

                Glide.with(context).load(bitmapBeans.get(position).url).fitCenter().into(viewById);
                name.setText(bitmapBeans.get(position).who);
                time.setText(bitmapBeans.get(position).publishedAt);

            }

        });

        container.addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return imageViewContainer.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}


//@Override
//public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(imageViewContainer.get(position % imageViewContainer.size()));
//        }
//
//@Override
//public Object instantiateItem(ViewGroup container, int position) {
//        View view = imageViewContainer.get(position % imageViewContainer.size());
//
//        // 为每一个page添加点击事件
//        view.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "Page 被点击了", Toast.LENGTH_SHORT).show();
//        }
//
//        });
//
//        container.addView(view);
//        return view;
//        }
//
//@Override
//public int getCount() {
//        return Integer.MAX_VALUE;
//        }
//
//@Override
//public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//        }
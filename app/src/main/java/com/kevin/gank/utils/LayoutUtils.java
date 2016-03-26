package com.kevin.gank.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LayoutUtils {

    /**
     * creasylai
     * 重新调整ListView的高度(记住只有LinearLayout才有measure方法，RelativeLayout会崩溃)
     */
    public static void setListViewHeightBasedOnChildren (ListView listView) {
        if (listView == null) {
            return;
        }
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 动态增加gridview的高度
     *
     * @param gridView
     */
    public static void setGridViewHeightBasedOnChildren (GridView gridView, int numColumns) {
        if (gridView == null) {
            return;
        }
        ListAdapter adapter = gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        //列数
        int totalHeight = 0;
        int size = adapter.getCount();
        //每隔columns个元素，增加item 的高度
        for (int i = 0; i < size; i += numColumns) {
            View item = adapter.getView(i, null, gridView);
            item.measure(0, 0);
            //计算高度和
            totalHeight += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public static void setExpandableListViewHeightBasedOnChildren (ExpandableListView listView) {
        //获取ListView对应的Adapter
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getGroupCount(); i < len; i++) {
            View listItem = listAdapter.getGroupView(i, true, null, listView);
            listItem.measure(0, 0);  //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight();
            for (int j = 0, chileLen = listAdapter.getChildrenCount(i); j < chileLen; j++) {
                View listChildItem = listAdapter.getChildView(i, j, false, null, listView);
                listChildItem.measure(0, 0);  //计算子项View 的宽高
                totalHeight += listChildItem.getMeasuredHeight();  //统计所有子项的总高度
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        if (listView.getDividerHeight() != -1) {
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        }
        listView.setLayoutParams(params);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px (Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip (Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 把ScrollView移动到底部
     *
     * @param scroll--ScrollView
     * @param inner--ScrollView的唯一子view，一般是Layout
     * @return
     * @author jianfei.zhang
     */
    public static void scroll2Bottom (final View scroll, final View inner) {
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            public void run () {
                if (scroll == null || inner == null) {
                    return;
                }

                int offset = inner.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0) {
                    offset = 0;
                }

                scroll.scrollTo(0, offset);
            }
        });
    }
}

package com.kevin.gank.protocol;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.kevin.gank.ui.MainActivity;
import com.kevin.gank.utils.DateTime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kai.xiong on 2016/3/25.
 */
public class NetUtils {


    static OkHttpClient client = new OkHttpClient();

    public static String run(String url) throws IOException {


        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 请求分类数据
     */

    public static void category(final String type, final int count, final int page, final Context context, final Class aClass) {

//       分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://gank.io/api/data/" + type + "/" + count + "/" + page)
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    context.startActivity(new Intent(context, aClass));

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }


    /**
     * 默认请求20个数据,第一页的数据
     *
     * @param type
     * @return
     * @throws IOException
     */
    public static void category(String type, Context context, Class tClass) throws IOException {

//       分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
        category(type, 20, 1, context, tClass);


    }

    @NonNull
    public static String getTodayData() throws IOException {
        String dateString = DateTime.getDateString();
        switch (DateTime.todayWeek()) {

            case "星期六":
                dateString = DateTime.formatTime(DateTime.beforeNDays(-1)).split(" ")[0].replace("-","/");
                break;
            case "星期日":
                dateString = DateTime.formatTime(DateTime.beforeNDays(-2)).split(" ")[0].replace("-","/");
                break;
        }
        return NetUtils.run("http://gank.io/api/day/" + dateString);
    }


}

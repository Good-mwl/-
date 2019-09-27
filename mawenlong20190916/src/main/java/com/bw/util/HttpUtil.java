package com.bw.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: 马文龙
 * data: 2019/9/16 08:8:58
 * 功能: 工具类
 */
public  class HttpUtil {

    public static HttpUtil util;
    private HttpUtil(){

    }
    public static synchronized HttpUtil getInstance(){
        if (util==null){
            util = new HttpUtil();
        }
        return util;
    }
    //判断网络
    public boolean getNetState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    //请求数据
    public String getNetData(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream stream = conn.getInputStream();
                String json = streamToJson(stream);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //请求图片
    public Bitmap getNetPic(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream stream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private String streamToJson(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String trmp = "";
        StringBuilder builder = new StringBuilder();
        while ((trmp = reader.readLine()) != null) {
            builder.append(trmp);
        }
        reader.close();
        return builder.toString();

    }
}

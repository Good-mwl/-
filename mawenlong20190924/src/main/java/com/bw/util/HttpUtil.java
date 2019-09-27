package com.bw.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: 马文龙
 * data: 2019/9/24 14:14:21
 * 功能:
 */
public class HttpUtil  {
    //单例模式
    public static  HttpUtil util;
    private HttpUtil (){}
    public  static  synchronized  HttpUtil getInstence(){
        if (util == null){
            util = new HttpUtil();
        }
        return util;
    }
    //判断网络
    public boolean getConn(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return info.isConnected();
        }
        return false;
    }
    //请求数据
    public String getData(String str){
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() == 200){
                InputStream inputStream =conn.getInputStream();
                String json = ReadJson(inputStream);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String ReadJson(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String temp="";
        StringBuilder builder = new StringBuilder();
        while ((temp = reader.readLine())!=null){
            builder.append(temp);
        }
        reader.close();
        return builder.toString();
    }

}

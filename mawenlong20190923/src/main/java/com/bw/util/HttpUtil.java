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
import java.net.URLConnection;

/**
 * author: 马文龙
 * data: 2019/9/23 19:19:06
 * 功能:
 */
public class HttpUtil {
    public static  HttpUtil utils;
    private HttpUtil(){}
    public static  synchronized HttpUtil getInstence(){
        if (utils == null){
            utils = new HttpUtil();
        }
        return utils;
    }
    public  boolean getNetType(Context context) {
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
            if (conn.getResponseCode()==200){
                InputStream inputStream =  conn.getInputStream();
                String json = ReadJson(inputStream);
                Log.d("aaab", "getData: "+json);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //图片
    public Bitmap getPic(String str){
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode()==200){
                InputStream inputStream =  conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

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
        while ((temp=reader.readLine())!=null){
            builder.append(temp);
        }
        reader.close();
        return builder.toString();
    }
}



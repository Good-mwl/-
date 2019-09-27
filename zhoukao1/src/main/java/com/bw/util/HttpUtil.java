package com.bw.util;

import android.content.Context;
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
 * data: 2019/9/2 21:21:32
 * 功能:
 */
public class HttpUtil {
    //1。判断网络连接
    public static boolean getNetState(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return info.isConnected();
        }
        return false;
    }
    //2.网络请求
    public static String getNetData(String str){
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //不能小写
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200){
                InputStream stream = conn.getInputStream();
                //流转字符串
                String json = streamToJson(stream);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private static String streamToJson(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String temp = "";
        StringBuilder builder = new StringBuilder();
        while ((temp = reader.readLine())!=null){
            builder.append(temp);
        }
        reader.close();
        return builder.toString();
    }

}

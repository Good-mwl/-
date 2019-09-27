package com.bw.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

/**
 * author: 马文龙
 * data: 2019/9/7 11:11:26
 * 功能:
 */
public class HttpUtil {
    //声明静态类对象
    public static HttpUtil util;

    //创建构造方法
    private HttpUtil() {
    }

    //创建外部调用方法
    public static synchronized HttpUtil getInstance() {
        if (util == null) {
            return new HttpUtil();
        }
        return util;
    }

    //判断网络
    public boolean getNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    //请求数据
    public String getNet(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream stream = conn.getInputStream();
                String name = streamToJson(stream);
                return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String streamToJson(InputStream stream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String temp = "";
        StringBuilder builder = new StringBuilder();
        while ((temp = reader.readLine()) != null) {
            builder.append(temp);
        }
        reader.close();

        return builder.toString();
    }
}

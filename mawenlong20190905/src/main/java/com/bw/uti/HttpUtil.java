package com.bw.uti;

import android.content.ComponentName;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author: 马文龙
 * data: 2019/9/5 14:14:27
 * 功能:
 */
public class HttpUtil {
        //1.声明静态类对象
        public static HttpUtil util;

        //2.创建私有构造方法
        private HttpUtil() {
        }

        //3.创建外部调用方法
        public static synchronized HttpUtil getInstance() {
            if (util == null) {
                util = new HttpUtil();
            }
            return util;
        }

        //网络判断
        public boolean getNetWork(Context context) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isConnected();
            }
            return false;
        }

        //请求数据
        public String getNe(String str) {
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
        //请求图片
        public Bitmap getNetPic(String pic){
            try {
                URL url = new URL(pic);
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

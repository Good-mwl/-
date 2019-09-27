package com.bw.async;

import android.os.AsyncTask;

import com.bw.util.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/7 14:14:25
 * 功能:
 */
public class MyDataTask extends AsyncTask<String,Void,String> {
    CallBack callBack;

    public MyDataTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String net = HttpUtil.getInstance().getNet(strings[0]);
        return net;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.getJson(s);
    }
    public interface  CallBack{
        void getJson(String str);
    }
}

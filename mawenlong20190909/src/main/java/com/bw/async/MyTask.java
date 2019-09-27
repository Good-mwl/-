package com.bw.async;

import android.os.AsyncTask;

import com.bw.util.NetUtil;

/**
 * author: 马文龙
 * data: 2019/9/9 09:9:31
 * 功能:
 */
public class MyTask extends AsyncTask<String,Void,String> {
    MyCallBack myCallBack;

    public MyTask(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String json = NetUtil.getInstance().getJson(strings[0]);
        return json;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        myCallBack.getJson(string);
    }
    public interface MyCallBack{
        void getJson(String json);
    }
}

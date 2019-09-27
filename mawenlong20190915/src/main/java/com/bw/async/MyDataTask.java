package com.bw.async;

import android.os.AsyncTask;

import com.bw.util.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/15 16:16:30
 * 功能:
 */
public class MyDataTask extends AsyncTask<String,Void,String> {
      DataCallBack dataCallBack;

    public MyDataTask(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        String netData = HttpUtil.getInstance().getNetData(strings[0]);
        return netData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dataCallBack.getJson(s);
    }
    public interface DataCallBack{
        void getJson(String s);
    }
}

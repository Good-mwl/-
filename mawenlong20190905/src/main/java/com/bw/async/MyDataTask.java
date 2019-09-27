package com.bw.async;

import android.os.AsyncTask;

import com.bw.uti.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/6 10:10:43
 * 功能:
 */
public class MyDataTask extends AsyncTask<String,Void,String> {
    CallBack callBack;

    public MyDataTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpUtil util = HttpUtil.getInstance();
        String utilNe = util.getNe(strings[0]);
        return utilNe;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callBack.getJson(s);
    }
    public interface CallBack{
        void getJson(String str);
    }

}


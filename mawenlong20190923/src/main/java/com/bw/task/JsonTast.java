package com.bw.task;

import android.os.AsyncTask;

import com.bw.util.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/23 19:19:06
 * 功能:
 */
public class JsonTast extends AsyncTask<String,Void,String> {
    getDataBack getDataBack;

    public JsonTast(JsonTast.getDataBack getDataBack) {
        this.getDataBack = getDataBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        return HttpUtil.getInstence().getData(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        getDataBack.dataBack(s);
    }
    public  interface getDataBack{
        void  dataBack(String s);
    }
}

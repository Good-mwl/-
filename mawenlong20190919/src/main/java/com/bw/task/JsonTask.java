package com.bw.task;

import android.os.AsyncTask;

import com.bw.util.Util;

/**
 * author: 马文龙
 * data: 2019/9/21 14:14:02
 * 功能:
 */
public class JsonTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        return Util.getHttp().getNetJson(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.getJson(s);
    }

    MyCallback callback;

    public JsonTask(MyCallback callback) {
        this.callback = callback;
    }

    public interface MyCallback {
        void getJson(String json);
    }
}


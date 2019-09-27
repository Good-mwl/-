package com.bw.async;

import android.os.AsyncTask;

import com.bw.util.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/24 16:16:26
 * 功能:
 */
public class MyAsync extends AsyncTask<String,Void,String> {
    MyDataBack myDataBack;

    public MyAsync(MyDataBack myDataBack) {
        this.myDataBack = myDataBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        return HttpUtil.getInstence().getData(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        myDataBack.dataBack(s);
    }
    //接口
    public  interface  MyDataBack{
        void  dataBack(String s);
    }
}


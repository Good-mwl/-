package com.bw.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bw.uti.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/6 10:10:40
 * 功能:
 */
public class MyPicTask extends AsyncTask<String,Void, Bitmap> {
    CallBack callBack;

    public MyPicTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpUtil util = HttpUtil.getInstance();
        Bitmap pic = util.getNetPic(strings[0]);
        return pic;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        callBack.getPic(bitmap);
    }
    public interface CallBack{
        void getPic(Bitmap bitmap);
    }
}

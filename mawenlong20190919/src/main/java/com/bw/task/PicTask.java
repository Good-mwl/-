package com.bw.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bw.util.Util;

/**
 * author: 马文龙
 * data: 2019/9/21 10:10:10
 * 功能:
 */
public class PicTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        return Util.getHttp().getNetPic(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        callback.getPic(bitmap);
    }

    MyCallback callback;

    public PicTask(MyCallback callback) {
        this.callback = callback;
    }

    public interface MyCallback {
        void getPic(Bitmap pic);
    }
}


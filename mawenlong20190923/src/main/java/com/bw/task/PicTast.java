package com.bw.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bw.util.HttpUtil;

/**
 * author: 马文龙
 * data: 2019/9/23 19:19:06
 * 功能:
 */
public class PicTast extends AsyncTask<String,Void, Bitmap> {
    getPicBack getPicBack;

    public PicTast(PicTast.getPicBack getDataBack) {
        this.getPicBack = getDataBack;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return HttpUtil.getInstence().getPic(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        getPicBack.dataBack(bitmap);
    }

    public  interface getPicBack{
        void  dataBack(Bitmap bitmap);
    }
}

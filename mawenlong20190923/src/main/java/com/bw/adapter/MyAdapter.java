package com.bw.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.bean.News;
import com.bw.mawenlong20190923.R;
import com.bw.task.PicTast;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/23 20:20:19
 * 功能:
 */
public class MyAdapter extends BaseAdapter {
    private List<News.ResultBean.NewsBean> list;
    private Context context;

    public MyAdapter(List<News.ResultBean.NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder =new ViewHolder();
            convertView =  View.inflate(context, R.layout.layout_tem,null);
            viewHolder.textView=convertView.findViewById(R.id.text_view);
            viewHolder.imageView=convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getTitle());
        new PicTast(new  PicTast.getPicBack() {
            @Override
            public void dataBack(Bitmap bitmap) {
                viewHolder.imageView.setImageBitmap(bitmap);
            }
        }).execute(list.get(position).getThumbnail_pic_s());
        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}


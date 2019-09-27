package com.bw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.bean.Music;
import com.bw.zhoukao1.R;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/2 21:21:36
 * 功能:
 */
public class MyAdapter extends BaseAdapter {
    private List<Music.ResultBean.ContentBean> list;
    private Context context;

    public MyAdapter(List<Music.ResultBean.ContentBean> list, Context context) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imgView);
            holder.text_title = convertView.findViewById(R.id.text_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //展示请求的图片
        Glide.with(context).load(list.get(position).getPic_small()).into(holder.imageView);
        //展示文字
        holder.text_title.setText(list.get(position).getAuthor());

        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView text_title;
    }
}

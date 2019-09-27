package com.bw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.bean.News;
import com.bw.mawenlong20190924.R;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/24 16:16:03
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
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        ViewHolder2 viewHolder2;
        if (getItemViewType(position) == 1){
            if (convertView == null){
                viewHolder1 = new ViewHolder1();
                convertView = View.inflate(context, R.layout.item1,null);
                viewHolder1.textView1 = convertView.findViewById(R.id.textView1);
                viewHolder1.imageView1 = convertView.findViewById(R.id.imgview1);
                convertView.setTag(viewHolder1);
            }else{
                viewHolder1 = (ViewHolder1) convertView.getTag();
            }
            viewHolder1.textView1.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(viewHolder1.imageView1);

        }else if (getItemViewType(position) == 2){
            if (convertView == null){
                viewHolder2 = new ViewHolder2();
                convertView = View.inflate(context,R.layout.item2,null);
                viewHolder2.textView3 = convertView.findViewById(R.id.textView3);
                viewHolder2.imageView3 = convertView.findViewById(R.id.imgview3);
                convertView.setTag(viewHolder2);
            }else{
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
            viewHolder2.textView3.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(viewHolder2.imageView3);
        }

        return convertView;
    }
    class ViewHolder1{
        TextView textView1;
        ImageView imageView1;
    }
    class ViewHolder2{
        TextView textView3;
        ImageView imageView3;
    }
}

package com.bw.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.base.BaseActivity;
import com.bw.bean.User;
import com.bw.mawenlong20190919.R;
import com.bw.task.PicTask;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/21 10:10:06
 * 功能:
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<User.ResultBean.NewsBean> list;

    public MyAdapter(Context context, List<User.ResultBean.NewsBean> list) {
        this.context = context;
        this.list = list;
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
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType() - 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder1 holder1;
        final ViewHolder2 holder2;
        int type = getItemViewType(position);
        if (type == 0) {

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list1, null);
                holder1 = new ViewHolder1();
                holder1.imageView = convertView.findViewById(R.id.imageView);
                holder1.textView = convertView.findViewById(R.id.textView);
                convertView.setTag(holder1);
            } else {
                holder1 = (ViewHolder1) convertView.getTag();
            }
            holder1.textView.setText(list.get(position).getTitle());
            new PicTask(new PicTask.MyCallback() {
                @Override
                public void getPic(Bitmap pic) {
                    holder1.imageView.setImageBitmap(pic);
                }
            }).execute(list.get(position).getThumbnail_pic_s());

        } else if (type == 1) {

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_list2, null);
                holder2 = new ViewHolder2();
                holder2.imageView = convertView.findViewById(R.id.imageView);
                holder2.textView = convertView.findViewById(R.id.textView);
                convertView.setTag(holder2);
            } else {
                holder2 = (ViewHolder2) convertView.getTag();
            }
            holder2.textView.setText(list.get(position).getTitle());
            new PicTask(new PicTask.MyCallback() {
                @Override
                public void getPic(Bitmap pic) {
                    holder2.imageView.setImageBitmap(pic);
                }
            }).execute(list.get(position).getThumbnail_pic_s());

        }

        return convertView;
    }

    class ViewHolder1 {
        TextView textView;
        ImageView imageView;
    }

    class ViewHolder2 {
        TextView textView;
        ImageView imageView;
    }
}

package com.bw.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.async.MyPicTask;
import com.bw.bean.News;
import com.bw.mawenlong20190905.R;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/6 10:10:36
 * 功能:
 */
public class MyAdapter extends BaseAdapter {
    private List<News.ResultBean.DataBean> list;
    private Context context;

    public MyAdapter(List<News.ResultBean.DataBean> list, Context context) {
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
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder1 holder1;
        final ViewHolder2 holder2;
        ViewHolder3 holder3;
        int type = getItemViewType(position);
        if (type == 0) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_layout1, null);
                holder1 = new ViewHolder1();
                holder1.textView1 = convertView.findViewById(R.id.textView1);
                holder1.imgView1 = convertView.findViewById(R.id.imgView1);
                convertView.setTag(holder1);
            } else {
                holder1 = (ViewHolder1) convertView.getTag();
            }
            holder1.textView1.setText(list.get(position).getTitle());
            new MyPicTask(new MyPicTask.CallBack() {
                @Override
                public void getPic(Bitmap bitmap) {
                    holder1.imgView1.setImageBitmap(bitmap);
                }
            }).execute(list.get(position).getThumbnail_pic_s());
        } else if (type == 1) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_layout2, null);
                holder2 = new ViewHolder2();
                holder2.textView2 = convertView.findViewById(R.id.textView2);
                holder2.imgView2_a = convertView.findViewById(R.id.imgView2_a);
                holder2.imgView2_b = convertView.findViewById(R.id.imgView2_b);
                convertView.setTag(holder2);
            } else {
                holder2 = (ViewHolder2) convertView.getTag();
            }
            holder2.textView2.setText(list.get(position).getTitle());
            new MyPicTask(new MyPicTask.CallBack() {
                @Override
                public void getPic(Bitmap bitmap) {
                    holder2.imgView2_a.setImageBitmap(bitmap);
                }
            }).execute(list.get(position).getThumbnail_pic_s());
            new MyPicTask(new MyPicTask.CallBack() {
                @Override
                public void getPic(Bitmap bitmap) {
                    holder2.imgView2_b.setImageBitmap(bitmap);
                }
            }).execute(list.get(position).getThumbnail_pic_s02());
        } else if (type == 2) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_layout3, null);
                holder3 = new ViewHolder3();
                holder3.textView3 = convertView.findViewById(R.id.textView3);
                convertView.setTag(holder3);
            } else {
                holder3 = (ViewHolder3) convertView.getTag();
            }
            holder3.textView3.setText(list.get(position).getTitle());

        }

        return convertView;
    }

    class ViewHolder1 {
        private TextView textView1;
        private ImageView imgView1;
    }

    class ViewHolder2 {
        private TextView textView2;
        private ImageView imgView2_a;
        private ImageView imgView2_b;
    }

    class ViewHolder3 {
        private TextView textView3;
    }
}

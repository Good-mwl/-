package com.bw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.bean.Goods;
import com.bw.mawenlong20190916.R;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/16 09:9:34
 * 功能:
 */
public class MyAdapter extends BaseAdapter {
    private List<Goods.DataBean> list;
    private Context context;

    public MyAdapter(List<Goods.DataBean> list, Context context) {
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
        return list.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder1 holder1;
        ViewHolder2 holder2;
        if (type==1){
            if (convertView==null){
                convertView = View.inflate(context, R.layout.item_layout1,null);
                holder1 = new ViewHolder1();
                holder1.text_name = convertView.findViewById(R.id.text_name);
                convertView.setTag(holder1);
            }else {
                holder1 = (ViewHolder1) convertView.getTag();
            }
            holder1.text_name.setText(list.get(position).getGoods_name());
        }else if (type==2){
            if (convertView==null){
                convertView = View.inflate(context,R.layout.item_layout2,null);
                holder2 = new ViewHolder2();
                holder2.img_icon = convertView.findViewById(R.id.img_icon);
                convertView.setTag(holder2);
            }else {
                holder2 = (ViewHolder2) convertView.getTag();
            }
            Glide.with(context).load(list.get(position).getGoods_thumb()).into(holder2.img_icon);
        }
        return convertView;
    }
    class ViewHolder1{
        private TextView text_name;
    }
    class ViewHolder2{
        private ImageView img_icon;
    }
}

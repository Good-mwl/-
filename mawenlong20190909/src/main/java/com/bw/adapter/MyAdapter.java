package com.bw.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.bean.Shop;
import com.bw.mawenlong20190909.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/9 09:9:38
 * 功能:
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Shop.DataBean> list = new ArrayList<>();

    public MyAdapter(Context context, List<Shop.DataBean> list) {
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
        return list.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position)==1){
            ViewHolder viewHolder;
            viewHolder = new ViewHolder();
            convertView = View.inflate(context,R.layout.item_layout1,null);
            viewHolder.textViewName1 = convertView.findViewById(R.id.text_view_name1);
            viewHolder.textViewPrice1 = convertView.findViewById(R.id.text_view_price1);
            viewHolder.imageView1 = convertView.findViewById(R.id.img_view1);
            convertView.setTag(viewHolder);
            viewHolder.textViewName1.setText(list.get(position).getGoods_name());
            viewHolder.textViewPrice1.setText(list.get(position).getCurrency_price());
            Glide.with(context).load(list.get(position).getGoods_thumb()).into(viewHolder.imageView1);
        }else {
            if (getItemViewType(position)==2){
                ViewHolder2 viewHolder2;
                viewHolder2 = new ViewHolder2();
                convertView = View.inflate(context,R.layout.item_layout2,null);
                viewHolder2.textViewName2 = convertView.findViewById(R.id.text_view_name2);
                viewHolder2.textViewPrice2 = convertView.findViewById(R.id.text_view_price2);
                viewHolder2.imageView2 = convertView.findViewById(R.id.img_view2);
                convertView.setTag(viewHolder2);

                viewHolder2.textViewName2.setText(list.get(position).getGoods_name());
                viewHolder2.textViewPrice2.setText(list.get(position).getCurrency_price());
                Glide.with(context).load(list.get(position).getGoods_thumb()).into(viewHolder2.imageView2);
            }
        }
        return convertView;
    }
    class ViewHolder{
        TextView textViewName1,textViewPrice1;
        ImageView imageView1;
    }
    class ViewHolder2{
        TextView textViewName2,textViewPrice2;
        ImageView imageView2;
    }
}

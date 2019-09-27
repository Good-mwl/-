package com.bw.zhoukao1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.adapter.MyAdapter;
import com.bw.bean.Music;
import com.bw.util.HttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textTitle;
    private ImageView imgView;
    private GridView gridView;
    //声明接口
    private String url = "http://blog.zhaoliang5156.cn/api/music/music.json";
    private List<Music.ResultBean.ContentBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //设置适配器
        myAdapter = new MyAdapter(list, MainActivity.this);
        gridView.setAdapter(myAdapter);
        //点击条目，吐司
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //吐司

            }
        });
        //判断网络
        boolean netState = HttpUtil.getNetState(MainActivity.this);
        if (netState){
            //有网
            new MyTask().execute(url);
        }else {
            //没网
            Toast.makeText(MainActivity.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
        }

    }
    //创建异步任务
    class MyTask extends AsyncTask<String,Void,String> {

        //做耗时操作
        @Override
        protected String doInBackground(String... strings) {
            //网络请求
            String data = HttpUtil.getNetData(strings[0]);
            return data;
        }
        //更新UI
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //解析
            Gson gson = new Gson();
            Music music = gson.fromJson(s, Music.class);
            //取出标题文字
            String title = music.getResult().getName();
            Log.i("aaa", "onPostExecute: "+title);
            textTitle.setText(title);
            String pic_s210 = music.getResult().getPic_s210();
            Glide.with(MainActivity.this).load(pic_s210).into(imgView);
            //刷新适配器
            list.addAll(music.getResult().getContent());
            myAdapter.notifyDataSetChanged();
        }
    }
    private void initView() {
        textTitle = findViewById(R.id.text_tle);
        imgView = findViewById(R.id.imgView);
        gridView = findViewById(R.id.gridView);
    }
}

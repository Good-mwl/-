package com.bw.mawenlong20190907;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.async.MyDataTask;
import com.bw.util.HttpUtil;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

public class MainActivity extends AppCompatActivity {

    private XListView xlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //判断网络
        HttpUtil util = HttpUtil.getInstance();        if (util.getNetWork(MainActivity.this)){
            initData();
        }else {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();

        }
    }

    private void initData() {
        //有网
        new MyDataTask(new MyDataTask.CallBack() {
            @Override
            public void getJson(String str) {
                Log.i("aaa", "getJson: "+str);
                //解析
                Gson gson =new Gson();

                
            }
        }).execute();


    }

    private void initView() {
        xlistview = findViewById(R.id.xListview);

    }
}

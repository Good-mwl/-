package com.bw.mawenlong20190924;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.mawenlong20190924.Main2Activity;
import com.bw.mawenlong20190924.R;


public class MainActivity extends AppCompatActivity {

    //①　完成欢迎页图1，2秒进入主页。
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                //跳转
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  延迟两秒跳转
        handler.sendEmptyMessageDelayed(1,2000);

    }
}


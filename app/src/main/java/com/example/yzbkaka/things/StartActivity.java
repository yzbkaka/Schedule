package com.example.yzbkaka.things;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        TimerTask task = new TimerTask() {  //设置开启app的动画
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,1000);
    }
}

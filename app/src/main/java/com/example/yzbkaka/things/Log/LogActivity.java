package com.example.yzbkaka.things.Log;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.things.Adapter.LogAdapter;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Log;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    private Button back;  //返回
    private RecyclerView logView;  //显示日志的View
    private List<String> logList = new ArrayList<>();  //log的列表
    private List<Log> dataList = new ArrayList<>();  //获得数据库中的Log
    private FloatingActionButton create;  //创建按钮
   public static LogAdapter logAdapter;  //适配器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        back = (Button)findViewById(R.id.back);
        logView = (RecyclerView) findViewById(R.id.log_list);
        create = (FloatingActionButton)findViewById(R.id.create);

        logView.setAdapter(logAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogActivity.this,LogCreateActivity.class);
                startActivity(intent);
            }
        });

        setLightMode();
    }


    @Override
    public void onStart(){
        super.onStart();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);  //设置为瀑布式布局
        logView.setLayoutManager(layoutManager);
        logAdapter = new LogAdapter(logList);

        dataList = LitePal.findAll(Log.class);
        if(dataList.size() > 0){
            logList.clear();
            for(Log log : dataList){
                logList.add(log.getLogWrite());
            }
        }
        else{
            logList.clear();
        }
        logView.setAdapter(logAdapter);
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

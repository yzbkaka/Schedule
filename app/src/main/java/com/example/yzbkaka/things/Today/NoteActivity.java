package com.example.yzbkaka.things.Today;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.things.Adapter.TodayAdapter;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class NoteActivity extends AppCompatActivity {
    private Button back;  //后退键
    private RecyclerView todayView;  //显示今天所有任务的ListView
    private List<Plan> todayList = new ArrayList<>();  //今天任务的列表
    private List<Plan> dataList = new ArrayList<>();  //获得Plan数据库的列表
    private FloatingActionButton create;  //创建新任务的按钮
    public static TodayAdapter todayAdapter;  //适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        back = (Button)findViewById(R.id.back);
        todayView = (RecyclerView) findViewById(R.id.today_list);
        create = (FloatingActionButton) findViewById(R.id.create);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);  //一定要为RecyclerView设置布局管理器
        todayView.setLayoutManager(layoutManager);

        //back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //create
        create.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, TodayCreateActivitty.class);
                //create.setVisibility(View.GONE);
                startActivity(intent);
            }
        });

        todayAdapter = new TodayAdapter(todayList);
        todayView.setAdapter(todayAdapter);

        setLightMode();
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onResume() {
        super.onResume();
        create.setVisibility(View.VISIBLE);  //右下角的创建按钮显示出来
        dataList = LitePal.findAll(Plan.class);

        Calendar calendar = Calendar.getInstance();  //获得今天的时间
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DATE));


        if(dataList.size() > 0){
            todayList.clear();
            for(Plan plan:dataList){
                if(plan.getYear().equals(year) && plan.getMonth().equals(month) && plan.getDay().equals(day) && plan.getStatus()== false){
                    todayList.add(plan);
                    //todayCount++;  //today统计数量加1
                }
            }
        }
        else{
            todayList.clear();
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onPause(){
        super.onPause();
        create.setVisibility(View.GONE);
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}

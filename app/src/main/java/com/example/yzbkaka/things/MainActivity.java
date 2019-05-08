package com.example.yzbkaka.things;


import android.content.Intent;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.yzbkaka.things.History.HistoryActivity;
import com.example.yzbkaka.things.Log.LogActivity;
import com.example.yzbkaka.things.Schedule.ScheduleViewActivity;
import com.example.yzbkaka.things.Setting.SettingActivity;
import com.example.yzbkaka.things.Today.NoteActivity;


public class MainActivity extends AppCompatActivity {
    private Button today;
    private Button schedule;
    private Button log;
    private Button history;
    private Button openDrawer;
    private Button setting;
    DrawerLayout mDrawerLayout;
    public static int todayCount = 0;  //全局变量，统计today中显示的数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        today = (Button)findViewById(R.id.select_today);
        schedule = (Button)findViewById(R.id.select_plan);
        log = (Button)findViewById(R.id.select_log);
        history = (Button)findViewById(R.id.select_history);
        openDrawer = (Button)findViewById(R.id.open_drawer);
        setting = (Button)findViewById(R.id.setting);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.draw_layout);



        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleViewActivity.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LogActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        openDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        setLightMode();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

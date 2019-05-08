package com.example.yzbkaka.things.Today;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.example.yzbkaka.things.MainActivity.todayCount;


public class TodayCreateActivitty extends AppCompatActivity {
    private EditText editText;  //写下的计划
    private Button finish;  //完成后的按钮
    private String write;  //输入的内容
    private Calendar calendar;  //获得时间
    Date date;
    Plan plan = new Plan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_create_activitty);
        editText = (EditText)findViewById(R.id.edit_text);
        finish = (Button)findViewById(R.id.finish);

        calendar = Calendar.getInstance();  //获得实例
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  //设置为中国区的时间

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write = String.valueOf(editText.getText());  //获得输入的内容
                if(!write.isEmpty()){
                        plan.setWritePlan(write);
                        plan.setYear(String.valueOf(calendar.get(Calendar.YEAR)));  //设置写下的时间,年
                        plan.setMonth(String.valueOf(calendar.get(Calendar.MONTH)+1));  //设置月份(系统中是从0开始的)
                        plan.setDay(String.valueOf(calendar.get(Calendar.DATE)));  //设置日期
                        date = new Date(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));  //设置plan中的date
                        plan.setCreateTime(date);
                        todayCount++;  //today统计数量加1
                        plan.save();
                }
                finish();
            }
        });

        setLightMode();
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

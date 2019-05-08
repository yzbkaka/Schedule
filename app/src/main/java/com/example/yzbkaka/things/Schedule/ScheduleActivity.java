package com.example.yzbkaka.things.Schedule;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.Today.TodayCreateActivitty;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.TimeZone;


public class ScheduleActivity extends AppCompatActivity {
    private Button back;
    private MaterialCalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        back = (Button)findViewById(R.id.back);
        calendarView = (MaterialCalendarView)findViewById(R.id.calendarView);

        Calendar calendar = Calendar.getInstance();  //得到今天的时间
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final String todayYear = String.valueOf(calendar.get(Calendar.YEAR));
        final String todayMonth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        final String todayDay = String.valueOf(calendar.get(Calendar.DATE));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String year = String.valueOf(date.getYear());  //得到选中的时间
                String month = String.valueOf(date.getMonth()+1);
                String day = String.valueOf(date.getDay());

                if(((year.equals(todayYear) && month.equals(todayMonth) && Integer.parseInt(day) < Integer.parseInt(day))) || (year.equals(todayYear) && Integer.parseInt(month) < Integer.parseInt(todayMonth)) || (Integer.parseInt(year) < Integer.parseInt(todayYear))){  //如果选中的是今天，则会创建失败
                    Toast.makeText(ScheduleActivity.this, "请选择未来的时间点哦", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(ScheduleActivity.this, ScheduleCreateActivity.class);
                    intent.putExtra("year",year);
                    intent.putExtra("month",month);
                    intent.putExtra("day",day);
                    startActivity(intent);
                }

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

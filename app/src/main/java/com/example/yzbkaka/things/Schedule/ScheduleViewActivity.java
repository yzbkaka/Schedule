package com.example.yzbkaka.things.Schedule;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.things.Adapter.ScheduleAdapter;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class ScheduleViewActivity extends AppCompatActivity {
    private Button back;
    private RecyclerView scheduleView;
    private List<Plan> dataList = new ArrayList<>();
    private List<Plan> scheduleList = new ArrayList<>();
    public static ScheduleAdapter scheduleAdapter;
    private FloatingActionButton create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);
        back = (Button)findViewById(R.id.back);
        scheduleView = (RecyclerView)findViewById(R.id.schedule_view);
        create = (FloatingActionButton)findViewById(R.id.create);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        scheduleView.setLayoutManager(manager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleViewActivity.this,ScheduleActivity.class);
                startActivity(intent);
            }
        });

        setLightMode();
    }


    @Override
    public void onResume(){
        super.onResume();
        dataList = LitePal.findAll(Plan.class);
        create.setVisibility(View.VISIBLE);


        Calendar calendar = Calendar.getInstance();  //get today's time
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DATE));

        if (dataList.size() > 0){
            scheduleList.clear();
            for(Plan plan : dataList){
                if((plan.getYear().equals(year) && plan.getMonth().equals(month) && Integer.parseInt(plan.getDay()) > Integer.parseInt(day) ) || ( plan.getYear().equals(year) && Integer.parseInt(plan.getMonth()) > Integer.parseInt(month) ) || (Integer.parseInt(plan.getYear()) > Integer.parseInt(year)) ){  //Anything that isn't today will show up in the plan list
                    scheduleList.add(plan);
                    //Sort
                }
            }
        }
        scheduleAdapter = new ScheduleAdapter(scheduleList);
        scheduleView.setAdapter(scheduleAdapter);
    }

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

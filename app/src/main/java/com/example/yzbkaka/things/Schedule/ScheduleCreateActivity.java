package com.example.yzbkaka.things.Schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ScheduleCreateActivity extends AppCompatActivity {
    private Button finish;
    private EditText editText;
    String write;
    Plan plan = new Plan();
    Date date;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);
        finish = (Button)findViewById(R.id.finish);
        editText = (EditText)findViewById(R.id.edit_text);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  //Set to the time in China

        Intent intent = getIntent();
        final String scheduleYear = intent.getStringExtra("year");
        final String scheduleMonth = intent.getStringExtra("month");
        final String scheduleDay = intent.getStringExtra("day");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write = editText.getText().toString();
                if(!write.isEmpty()){
                    plan.setWritePlan(write);
                    plan.setYear(scheduleYear);
                    plan.setMonth(scheduleMonth);
                    plan.setDay(scheduleDay);
                    date = new Date(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE));
                    plan.save();
                    Toast.makeText(ScheduleCreateActivity.this, "Created successfully, remember to finish it", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

        });
    }
}

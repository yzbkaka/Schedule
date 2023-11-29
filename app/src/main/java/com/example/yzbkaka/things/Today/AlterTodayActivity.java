package com.example.yzbkaka.things.Today;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import org.litepal.LitePal;

import static com.example.yzbkaka.things.MainActivity.todayCount;

public class AlterTodayActivity extends AppCompatActivity {
    private EditText editText;
    private Button finish;
    Plan plan = new Plan();
    String oldWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_today);

        editText = (EditText) findViewById(R.id.old_text);
        finish = (Button)findViewById(R.id.finish);
        Intent intent = getIntent();
        oldWrite = intent.getStringExtra("write");  //Get the previous data
        editText.setText(oldWrite);  //Display the previous data on the EditText
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newWrite = editText.getText().toString();  //Get the new data after user modification
                if(newWrite.isEmpty()){
                    LitePal.deleteAll(Plan.class,"writePlan = ?",oldWrite);  //Automatically deleted if empty after modification
                    todayCount--;  //After deletion, the number of statistics is reduced by 1
                }
                else{
                    plan.setWritePlan(newWrite);  //LitePal syntax, set new content first, then look for conditional updates
                    plan.updateAll("writePlan = ?",oldWrite);
                    Toast.makeText(AlterTodayActivity.this, "Modify Successfully", Toast.LENGTH_SHORT).show();
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

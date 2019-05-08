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
        oldWrite = intent.getStringExtra("write");  //得到之前的数据
        editText.setText(oldWrite);  //将之前的数据显示在EditText上

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newWrite = editText.getText().toString();  //得到用户修改之后的新数据
                if(newWrite.isEmpty()){
                    LitePal.deleteAll(Plan.class,"writePlan = ?",oldWrite);  //如果修改之后为空则自动删除
                    todayCount--;  //删除之后则统计数量减1
                }
                else{
                    plan.setWritePlan(newWrite);  //LitePal语法，先设置新的内容，再寻找条件更新
                    plan.updateAll("writePlan = ?",oldWrite);
                    Toast.makeText(AlterTodayActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
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

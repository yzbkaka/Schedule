package com.example.yzbkaka.things.Log;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Log;

import org.litepal.LitePal;

public class AlterLogActivity extends AppCompatActivity {
    private Button back;
    private  Button save;
    private EditText editText;
    private String oldLog;
    Log log = new Log();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_log);

        back = (Button)findViewById(R.id.back);
        save = (Button)findViewById(R.id.save);
        editText = (EditText)findViewById(R.id.edit_text);
        Intent intent = getIntent();
        oldLog = intent.getStringExtra("log");  //获得之前的log内容
        editText.setText(oldLog);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newLog = editText.getText().toString();
                if(newLog.isEmpty()){
                    LitePal.deleteAll(Log.class,"logWrite = ?",oldLog);
                }
                else{
                    log.setLogWrite(newLog);
                    log.updateAll("logWrite = ?",oldLog);  //不能使用save的方法
                    Toast.makeText(AlterLogActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
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

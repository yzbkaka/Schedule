package com.example.yzbkaka.things.Log;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Log;

public class LogCreateActivity extends AppCompatActivity {
    private Button back;
    private EditText editText;
    private Button save;
    private String write;
    Log log = new Log();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_create);
        back = (Button)findViewById(R.id.back);
        editText = (EditText)findViewById(R.id.edit_text);
        save = (Button)findViewById(R.id.save);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write = editText.getText().toString();
                if(!write.isEmpty()){
                    log.setLogWrite(write);
                    log.save();
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

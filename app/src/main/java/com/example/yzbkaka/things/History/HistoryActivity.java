package com.example.yzbkaka.things.History;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yzbkaka.things.Adapter.HistoryAdapter;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Button back;
    private RecyclerView historyView;
    private Button clear;
    private List<Plan> historyList = new ArrayList<>();
    private List<Plan> dataList = new ArrayList<>();
    private HistoryAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        back = (Button)findViewById(R.id.back);
        historyView = (RecyclerView)findViewById(R.id.history_view);
        clear = (Button)findViewById(R.id.clear);
        dataList = LitePal.findAll(Plan.class);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        historyView.setLayoutManager(manager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {  //点击之后会清空列表
            @Override
            public void onClick(View view) {
                Toast.makeText(HistoryActivity.this, "清空！", Toast.LENGTH_SHORT).show();
                historyList.clear();
                myAdapter.notifyDataSetChanged();  //适配器在每一次list更新的时候都需要进行一次更新
                LitePal.deleteAll(Plan.class,"status = ?","1");  //删除所有status为true的plan
            }
        });

        if(dataList.size() > 0){
            historyList.clear();
            for(Plan plan : dataList){
                if(plan.getStatus() == true){  //如果状态是完成，则加入到历史列表当中
                    historyList.add(plan);
                }
            }
        }
        myAdapter = new HistoryAdapter(historyList);
        historyView.setAdapter(myAdapter);
        setLightMode();
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}

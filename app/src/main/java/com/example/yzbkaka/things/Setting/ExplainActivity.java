package com.example.yzbkaka.things.Setting;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yzbkaka.things.R;

import java.util.Timer;
import java.util.TimerTask;

public class ExplainActivity extends AppCompatActivity {
    private Button back;
    private LinearLayout explain1;
    private LinearLayout explain2;
    private LinearLayout explain3;
    private LinearLayout explain4;
    private LinearLayout explain5;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        back = (Button)findViewById(R.id.back);
        explain1 = (LinearLayout)findViewById(R.id.explain_one);
        explain2 = (LinearLayout)findViewById(R.id.explain_two);
        explain3 = (LinearLayout)findViewById(R.id.explain_three);
        explain4 = (LinearLayout)findViewById(R.id.explain_four);
        explain5 = (LinearLayout)findViewById(R.id.explain_five);
        img1 = (ImageView)findViewById(R.id.explain_button_one);
        img2 = (ImageView)findViewById(R.id.explain_button_two);
        img3 = (ImageView)findViewById(R.id.explain_button_three);
        img4 = (ImageView)findViewById(R.id.explain_button_four);
        img5 = (ImageView)findViewById(R.id.explain_button_five);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img1.setImageResource(R.drawable.yes);
                final Animation animation = AnimationUtils.loadAnimation(ExplainActivity.this,R.anim.today_anim);  //设置动画
                explain1.startAnimation(animation);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                explain1.setVisibility(View.GONE);
                            }
                        });
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 800);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img2.setImageResource(R.drawable.yes);
                Animation animation = AnimationUtils.loadAnimation(ExplainActivity.this,R.anim.today_anim);  //设置动画
                explain2.startAnimation(animation);

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                explain2.setVisibility(View.GONE);
                            }
                        });
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task,800);


            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img3.setImageResource(R.drawable.yes);
                Animation animation = AnimationUtils.loadAnimation(ExplainActivity.this,R.anim.today_anim);  //设置动画
                explain3.startAnimation(animation);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                explain3.setVisibility(View.GONE);
                            }
                        });
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task,800);

            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img4.setImageResource(R.drawable.yes);
                Animation animation = AnimationUtils.loadAnimation(ExplainActivity.this,R.anim.today_anim);  //设置动画
                explain4.startAnimation(animation);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                explain4.setVisibility(View.GONE);
                            }
                        });
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task,800);

            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img5.setImageResource(R.drawable.yes);
                Animation animation = AnimationUtils.loadAnimation(ExplainActivity.this,R.anim.today_anim);  //设置动画
                explain5.startAnimation(animation);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                explain5.setVisibility(View.GONE);
                            }
                        });
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task,800);
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

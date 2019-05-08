package com.example.yzbkaka.things.Service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.Today.NoteActivity;

import static com.example.yzbkaka.things.MainActivity.todayCount;
import static com.example.yzbkaka.things.Setting.SettingActivity.count;

public class SendMessage extends Service {
    public SendMessage() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(count%2 == 0){  //根据次数来切换图片
                    Intent notificationIntent = new Intent(SendMessage.this,NoteActivity.class);  //开始设置通知效果
                    PendingIntent pendingIntent = PendingIntent.getActivity(SendMessage.this,0, notificationIntent,0);  //设置PendingIntent
                    NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){  //判断系统是否大于8.0
                        NotificationChannel notificationChannel = new NotificationChannel("channel","channel",NotificationManager.IMPORTANCE_HIGH);
                        manager.createNotificationChannel(notificationChannel);
                        Notification notification = new NotificationCompat.Builder(SendMessage.this,"channel")
                                .setContentTitle("任务清单已送达")
                                .setContentText("小主，今天还有" + todayCount + "件任务要完成哦！")
                                .setWhen(System.currentTimeMillis())  //发送通知的时间
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo144))  //设置大图标
                                .setContentIntent(pendingIntent)  //设置通知的点击效果
                                .setAutoCancel(true)  //设置点击之后自动关闭通知
                                .build();

                        if(todayCount > 0){
                            manager.notify(1,notification);  //显示通知
                        }
                    }

                    else{
                        Notification notification = new NotificationCompat.Builder(SendMessage.this)
                                .setContentTitle("今天的任务清单")
                                .setContentText("小主今天还有" + todayCount + "件计划要完成哦！")
                                .setWhen(System.currentTimeMillis())  //发送通知的时间
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo144))  //设置大图标
                                .setContentIntent(pendingIntent)  //设置通知的点击效果
                                .setAutoCancel(true)  //设置点击之后自动关闭通知
                                .build();

                        if(todayCount > 0){
                            manager.notify(1,notification);  //显示通知
                        }
                    }
                }

                else{
                }

            }
        }).start();

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);  //设置定时服务
        int fourHour = 4*60*60*1000;  //单位是毫秒（4小时）
        long time = SystemClock.elapsedRealtime() + fourHour;
        Intent i = new Intent(this,SendMessage.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,pi);
        return super.onStartCommand(intent,flags,startId);
    }
}

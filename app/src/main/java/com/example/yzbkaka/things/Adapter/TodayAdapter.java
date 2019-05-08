package com.example.yzbkaka.things.Adapter;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.things.MainActivity;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.Today.AlterTodayActivity;
import com.example.yzbkaka.things.Today.NoteActivity;
import com.example.yzbkaka.things.db.Plan;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

import org.litepal.LitePal;

import static com.example.yzbkaka.things.MainActivity.todayCount;
import static com.example.yzbkaka.things.Today.NoteActivity.todayAdapter;


/**
 * Created by yzbkaka on 19-4-4.
 */

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder> {
    private List<Plan> mDataList;
    private static int imagePosition;  //将点击前面框框顺序的位置设置为静态变量

    static class ViewHolder extends RecyclerView.ViewHolder{
        View todayView;
        ImageView imageView;
        TextView todayText;
        Button delete;

        public ViewHolder(View view){
            super(view);
            todayView = view;
            imageView = (ImageView)view.findViewById(R.id.no_finish);
            todayText = (TextView)view.findViewById(R.id.today_plan);
            delete = (Button)view.findViewById(R.id.delete);
        }
    }


    public TodayAdapter(List<Plan> dataList){
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_item,parent,false);  //加载子项布局
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder , final int i){
        final Plan plan = mDataList.get(i);
        holder.todayText.setText(plan.getWritePlan());

        final Handler handler = new Handler() {  //异步消息处理机制来在非主线程中来修改UI

            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        mDataList.remove(imagePosition);  //将该Plan从列表中移除（此时数据库中的没有移除）
                        todayAdapter.notifyDataSetChanged();  //更新适配器以达到更新列表的效果
                        holder.imageView.setImageResource(R.drawable.no);  //将前面的框框换回成灰色
                        break;

                    default:
                        break;
                }
            }
        };

        holder.todayView.setOnClickListener(new View.OnClickListener() {  //当点击文字时会进入修改activity
            @Override
            public void onClick(View view) {
                int writePosition = holder.getAdapterPosition();
                Plan viewPlan = mDataList.get(writePosition);
                String writeToday = viewPlan.getWritePlan();
                Intent intent = new Intent(view.getContext(),AlterTodayActivity.class);
                intent.putExtra("write",writeToday);
                view.getContext().startActivity(intent);
            }
        });


        holder.imageView.setOnClickListener(new View.OnClickListener() {  //当点击前面的勾选框时会改变勾选框的图片
            @Override
            public void onClick(View view) {
                imagePosition = holder.getAdapterPosition();
                Plan ImagePlan = mDataList.get(imagePosition);
                holder.imageView.setImageResource(R.drawable.yes);  //切换图片
                ImagePlan.setStatus(true);  //状态设置为完成
                ImagePlan.save();  //保存该状态
                todayCount--;  //移除之后数量减1

                TimerTask task1 = new TimerTask() {  //设置定时任务
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);  //发送异步Message
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task1, 800);  //设置延时的时间，单位是毫秒

                Animation animation = AnimationUtils.loadAnimation(view.getContext(),R.anim.today_anim);  //设置动画
                holder.todayView.startAnimation(animation);
            }
        });


        holder.todayView.setOnLongClickListener(new View.OnLongClickListener() {  //长按机制
            @Override
            public boolean onLongClick(View view) {  //长按效果
                holder.delete.setVisibility(View.VISIBLE);  //将删除按钮显示出来


                return true;
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deletePosition = holder.getAdapterPosition();  //得到点击的顺序
                Plan deletePlan = mDataList.get(deletePosition);
                String deletePlanWrite = deletePlan.getWritePlan();
                mDataList.remove(deletePosition);
                todayCount--;  //数量减少
                todayAdapter.notifyDataSetChanged();  //更新适配器
                LitePal.deleteAll(Plan.class,"writePlan = ?",deletePlanWrite);  //将该条today删除


                TimerTask deleteTask = new TimerTask() {
                    @Override
                    public void run() {
                        holder.delete.setVisibility(View.INVISIBLE);
                    }
                };
                Timer deleteTimer = new Timer();
                deleteTimer.schedule(deleteTask,0);  //一秒钟之后自动隐藏删除按钮
            }
        });
    }



    @Override
    public int getItemCount(){
        return mDataList.size();
    }
}

package com.example.yzbkaka.things.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.Schedule.AlterScheduleActivity;
import com.example.yzbkaka.things.Today.AlterTodayActivity;
import com.example.yzbkaka.things.db.Plan;

import org.litepal.LitePal;

import java.util.List;

import static com.example.yzbkaka.things.Schedule.ScheduleViewActivity.scheduleAdapter;

/**
 * Created by yzbkaka on 19-4-13.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<Plan> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View scheduleView;
        TextView schedule;
        ImageView kuang;
        TextView time;
        Button delete;


        public ViewHolder(View view){
            super(view);
            scheduleView = view;
            schedule = (TextView)view.findViewById(R.id.schedule_plan);
            kuang = (ImageView)view.findViewById(R.id.kuang);
            time = (TextView)view.findViewById(R.id.schedule_time);
            delete = (Button)view.findViewById(R.id.delete_schedule);
        }
    }


    public ScheduleAdapter(List<Plan> dataList){
        mDataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item,parent,false);  //加载子项布局
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder , final int position){
        Plan plan = mDataList.get(position);
        holder.time.setText(plan.getMonth() + "Month" + plan.getDay() + "Day");
        holder.schedule.setText(plan.getWritePlan());

        holder.scheduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int writePosition = holder.getAdapterPosition();
                Plan viewPlan = mDataList.get(writePosition);
                String writeToday = viewPlan.getWritePlan();
                Intent intent = new Intent(view.getContext(),AlterScheduleActivity.class);
                intent.putExtra("write",writeToday);
                view.getContext().startActivity(intent);
            }
        });

        holder.scheduleView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.delete.setVisibility(View.VISIBLE);
                return true;
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scheduleDeletePosition = holder.getAdapterPosition();
                Plan scheduleDeletePlan = mDataList.get(scheduleDeletePosition);
                String scheduleDeletePlanWrite = scheduleDeletePlan.getWritePlan();
                mDataList.remove(scheduleDeletePosition);
                scheduleAdapter.notifyDataSetChanged();
                LitePal.deleteAll(Plan.class,"writePlan = ?",scheduleDeletePlanWrite);
                holder.delete.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public int getItemCount(){
        return mDataList.size();
    }
}

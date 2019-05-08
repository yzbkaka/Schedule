package com.example.yzbkaka.things.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Plan;

import java.util.List;

/**
 * Created by yzbkaka on 19-4-12.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Plan> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView history;
        ImageView right;
        TextView time;


        public ViewHolder(View view){
            super(view);
            history = (TextView)view.findViewById(R.id.history_plan);
            right = (ImageView)view.findViewById(R.id.right_kuang);
            time = (TextView)view.findViewById(R.id.time);
         }
    }

    public HistoryAdapter(List<Plan> dataList){
        mDataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);  //加载子项布局
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder , final int position){
        Plan plan = mDataList.get(position);
        holder.time.setText(plan.getMonth() + "月" + plan.getDay() + "日");
        holder.history.setText(plan.getWritePlan());
    }


    @Override
    public int getItemCount(){
        return mDataList.size();
    }
}

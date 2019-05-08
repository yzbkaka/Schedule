package com.example.yzbkaka.things.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzbkaka.things.Log.AlterLogActivity;
import com.example.yzbkaka.things.R;
import com.example.yzbkaka.things.db.Log;

import org.litepal.LitePal;

import java.util.List;

import static com.example.yzbkaka.things.Log.LogActivity.logAdapter;


/**
 * Created by yzbkaka on 19-4-7.
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    private List<String> mLogList;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View logView;
        CardView cardView;
        TextView logText;
        ImageView imageView;
        Button deleteLog;

        public ViewHolder(View view){
            super(view);
            logView = view;
            cardView = (CardView)view;
            logText = (TextView)view.findViewById(R.id.log);
            imageView = (ImageView)view.findViewById(R.id.image_view);
            deleteLog = (Button)view.findViewById(R.id.delete_log);
        }
    }

    public LogAdapter(List<String> logList){
        mLogList = logList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item,parent,false);  //加载子项布局
        final ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        String writeLog = mLogList.get(position);
        holder.logText.setText(writeLog);
        holder.logView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String writeLog = mLogList.get(position);
                Intent intent = new Intent(view.getContext(),AlterLogActivity.class);  //自动获取上下文
                intent.putExtra("log",writeLog);  //传输log内容
                view.getContext().startActivity(intent);  //自动获取上下文后才能够启动活动
            }
        });

        holder.logView.setOnLongClickListener(new View.OnLongClickListener() {  //设置长按按钮功能
            @Override
            public boolean onLongClick(View view) {
                holder.deleteLog.setVisibility(View.VISIBLE);  //将该按钮显示出来
                return true;
            }
        });

        holder.deleteLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deleteLogPosition = holder.getAdapterPosition();
                String deleteLogWrite = mLogList.get(deleteLogPosition);  //获得到该位置的内容
                mLogList.remove(deleteLogPosition);
                holder.deleteLog.setVisibility(View.INVISIBLE);  //再次隐藏按钮
                logAdapter.notifyDataSetChanged();  //更新适配器
                LitePal.deleteAll(Log.class,"logWrite = ?",deleteLogWrite);  //从数据库中删除

            }
        });
    }



    @Override
    public int getItemCount(){
        return mLogList.size();
    }
}

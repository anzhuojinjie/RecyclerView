package com.joey.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<String> data;

    public RecyclerViewAdapter(List<String> data) {
        this.data = data;
    }

    public List<String> getDataList(){
        return data;
    }

    //创建新的viewHolder 被layoutmanager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //将界面与数据进行绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position));
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data.size();
    }

    //自定义的viewHoler 持有每个item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}

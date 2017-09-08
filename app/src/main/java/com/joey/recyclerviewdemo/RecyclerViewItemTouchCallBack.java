package com.joey.recyclerviewdemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RecyclerViewItemTouchCallBack extends ItemTouchHelper.Callback {
    private RecyclerViewAdapter adapter;

    public RecyclerViewItemTouchCallBack(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    //通过返回值来处理是否某次滑动或者拖拽事件
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipFlag = 0;
            return makeMovementFlags(dragFlags,swipFlag);
        }else{
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipFlag = ItemTouchHelper.START | ItemTouchHelper.END;//返回0的时候不能监听的侧滑动作
            return makeMovementFlags(dragFlags,swipFlag);
        }
    }

    //当长按进入拖拽状态时，会不停的调用该方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//拖拽item的下标
        int toPosition = target.getAdapterPosition();//目标item的下标

        if (toPosition == 0){//如果拖动到了第一个位置就返回false表示不能执行此次拖动
            return false;
        }

        if (fromPosition<toPosition){
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(adapter.getDataList(),i,i+1);
            }
        }else{
            for (int i = fromPosition; i > toPosition ; i--) {
                Collections.swap(adapter.getDataList(),i,i-1);
            }
        }
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    //滑动删除的事件
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //这里实现侧滑删除
        int deletePosition = viewHolder.getAdapterPosition();
        adapter.notifyItemRemoved(deletePosition);
        adapter.getDataList().remove(deletePosition);
    }


    //当开始拖拽的时候调用
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE ){
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    //完成拖拽手指松开的时候调用
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(recyclerView.getContext(),R.color.colorAccent));
    }

    //该方法控制是否能拖拽，默认true，先改为false
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}

package com.joey.recyclerviewdemo;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView的自定义点击监听器
 * Created by joey on 2017/9/8.
 */

public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;
    public OnRecyclerItemClickListener(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);
    public abstract void onLongClick(RecyclerView.ViewHolder viewHolder);

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener{
        //一次单独的轻触抬起操作就是一次点击事件
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childViewUnder != null){
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                onItemClick(childViewHolder);
            }
            return true;
        }

        //长按屏幕超过一定时长就会触发，就是一次长按事件
        @Override
        public void onLongPress(MotionEvent e) {
            View childViewHolder = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childViewHolder != null){
                RecyclerView.ViewHolder childViewUnder = mRecyclerView.getChildViewHolder(childViewHolder);
                onLongClick(childViewUnder);
            }
        }
    }
}

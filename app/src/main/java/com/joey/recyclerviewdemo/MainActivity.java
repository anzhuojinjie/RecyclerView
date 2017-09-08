package com.joey.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv1,rv2;
    private List<String> data;
    private RecyclerViewAdapter adapter1,adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = (RecyclerView) findViewById(R.id.rv1);
        rv2 = (RecyclerView) findViewById(R.id.rv2);
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("安卓进阶之路"+i);
        }
        adapter1 = new RecyclerViewAdapter(data);
        adapter2 = new RecyclerViewAdapter(data);

        rv1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv1.addItemDecoration(new DividerListItemDecoration(MainActivity.this,DividerListItemDecoration.VERTICAL_LIST));
        rv1.setAdapter(adapter1);
        final ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(new RecyclerViewItemTouchCallBack(adapter1));
        itemTouchHelper1.attachToRecyclerView(rv1);
        rv1.addOnItemTouchListener(new OnRecyclerItemClickListener(rv1) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "点击了"+viewHolder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "长按了"+viewHolder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
                if (viewHolder.getLayoutPosition() != 0){
                    itemTouchHelper1.startDrag(viewHolder);
                }
            }
        });


        rv2.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        rv2.addItemDecoration(new DividerGridItemDecoration(MainActivity.this));
        rv2.setAdapter(adapter2);
        final ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new RecyclerViewItemTouchCallBack(adapter2));
        itemTouchHelper2.attachToRecyclerView(rv2);
        rv2.addOnItemTouchListener(new OnRecyclerItemClickListener(rv2) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "点击了"+viewHolder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "长按了"+viewHolder.getLayoutPosition(), Toast.LENGTH_SHORT).show();
                if (viewHolder.getLayoutPosition() != 0){
                    itemTouchHelper2.startDrag(viewHolder);
                }
            }
        });

    }
}

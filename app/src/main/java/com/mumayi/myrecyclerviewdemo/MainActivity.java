package com.mumayi.myrecyclerviewdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mumayi.myrecyclerviewdemo.adapter.MyRecyclerAdapter;
import com.mumayi.myrecyclerviewdemo.interf.RecyclerItemListener;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<String> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView la_recycler = (RecyclerView) findViewById(R.id.la_recycler);
        final SwipeRefreshLayout la_refresh = (SwipeRefreshLayout) findViewById(R.id.la_refresh);
        //线性
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //网格
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        //瀑布流
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        la_recycler.setLayoutManager(linearLayoutManager);


        for (int i = 0; i < 50; i++) {

            list.add("heheda" + i);
        }

        final MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this, R.layout.recycler_item, list);

        la_recycler.setAdapter(myRecyclerAdapter);


        //设置下拉刷新的效果,模拟效果,具体业务逻辑未实现

        la_refresh.setColorSchemeColors(Color.RED);
        la_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                la_refresh.setRefreshing(true);

                la_recycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        la_refresh.setRefreshing(false);
                    }
                }, 2000);


            }


        });


        //设置动画
        la_recycler.setItemAnimator(new DefaultItemAnimator());//设置条目的动画效果

        //在adapter 里定义了public 方法 ,供外界调用

        myRecyclerAdapter.setOnItemClickListener(new RecyclerItemListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(MainActivity.this, "点击条目了~~" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {

                myRecyclerAdapter.removeItem(position);

                Toast.makeText(MainActivity.this, "删除条目~~" + position, Toast.LENGTH_SHORT).show();

            }
        });


    }
}

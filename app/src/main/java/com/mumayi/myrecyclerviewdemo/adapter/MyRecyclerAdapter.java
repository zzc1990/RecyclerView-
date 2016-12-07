package com.mumayi.myrecyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mumayi.myrecyclerviewdemo.R;
import com.mumayi.myrecyclerviewdemo.interf.RecyclerItemListener;

import java.util.ArrayList;

/**
 * author: zzc-1990
 * created on: 2016/12/5 18:15
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {//泛型的名字根据本类定义


    private Context context;
    private int anInt;
    private ArrayList<String> list;
    private RecyclerItemListener recyclerItemListener;

    public MyRecyclerAdapter(Context context, int recycler_item, ArrayList<String> list) {

        this.context = context;
        this.anInt = recycler_item;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(anInt, null);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.la_tv.setText(list.get(position));

        //设置点击事件
        holder.la_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerItemListener.onItemClick(holder.itemView, position);//条目点击事件
            }
        });

        holder.la_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                recyclerItemListener.onItemLongClick(holder.itemView, position);

                return true;//返回true 这样触发了onLongClick后便不会再触发onClick了
            }
        });

    }

    public void setOnItemClickListener(RecyclerItemListener listener) {

        this.recyclerItemListener = listener;

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView la_image;

        public TextView la_tv;

        public ViewHolder(View itemView) {
            super(itemView);

            //找控件这部分在onBind 里也可以
            la_image = (ImageView) itemView.findViewById(R.id.la_image);
            la_tv = (TextView) itemView.findViewById(R.id.la_tv);

        }

    }


    public void removeItem(int position) {

        list.remove(position);

        notifyItemRemoved(position);//删除recycler 中的一条数据

        /**
         * 若不使用该方法通知适配器数据已经变了，notifyItemRemoved会导致item下标错乱
         * 因为删除某一项时调用notifyItemRemoved后，显示的item是不会调用onBind方法的，
         * 所以position并没有被刷新。这时候得到的position值就是错误的。
         */

        notifyItemChanged(position, list.size());//通知更新数据


    }
}

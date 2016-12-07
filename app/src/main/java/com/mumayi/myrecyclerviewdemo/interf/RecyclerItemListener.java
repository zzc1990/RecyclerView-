package com.mumayi.myrecyclerviewdemo.interf;

import android.view.View;

/**
 * author: zzc-1990
 * created on: 2016/12/6 0:09
 */
public interface RecyclerItemListener {

    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);


}

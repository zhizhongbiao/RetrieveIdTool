package com.ybs.base_module.base.adapter;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhizhongbiao on 2018/6/13.
 */

public interface OnRvItemClickedListener<T, A extends RecyclerView.Adapter> {

    void onItemClick(View v, A adapter, T data, int pos);
}

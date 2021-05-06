package com.ybs.base_module.base.adapter;


import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ybs.distributenettool.base.adapter.DataHelper;

import java.util.List;


/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RvHolder> implements DataHelper<T> {
    public int selectedPos = -1;
    protected List<T> dataList;
    protected Context mContext;
    protected int layoutResourceId;

    public CommonRecyclerAdapter(List<T> dataList, Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    public CommonRecyclerAdapter(List<T> dataList, Context mContext, int layoutResourceId) {
        this(dataList, mContext);
        this.layoutResourceId = layoutResourceId;
    }

    public void updateSelectedPos(int indexOf) {
        this.selectedPos = indexOf;
        notifyDataSetChanged();
    }

    public boolean isOriginalPos() {
        return selectedPos == -1;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 999) {
            return new RvHolder(UIUtils.inflate(getTitleLayout(), parent));
        } else {
            return new RvHolder(UIUtils.inflate(layoutResourceId, parent));
        }
    }

    protected int getTitleLayout() {
        return -1;
    }


    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        T data = dataList.get(position);
        bindData(holder, data, position);
    }

    protected abstract void bindData(RvHolder holder, T data, int position);

    @Override
    public int getItemViewType(int position) {
        return layoutResourceId;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public List<T> getDataSet() {
        return dataList;
    }


    @Override
    public void updateAll(List<T> newDatas) {
        dataList = newDatas;
        notifyDataSetChanged();
    }


    @Override
    public void addAll(List<T> newDatas) {

        if (dataList != null) {
            dataList.addAll(newDatas);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        selectedPos = -1;
        if (dataList != null) {
            dataList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void addHead(T data) {
        if (dataList != null) {
            dataList.add(0, data);
            notifyItemInserted(0);
        }
    }

    @Override
    public void append(T data) {
        if (dataList != null) {
            dataList.add(data);
            notifyItemInserted(getItemCount() - 1);
        }
    }

    @Override
    public void replace(int index, T data) {
        if (dataList != null) {
            dataList.add(index, data);
            dataList.remove(index + 1);
            notifyItemRangeChanged(index, 2);
        }
    }

    @Override
    public void replace(T oldData, T newData) {
        if (dataList != null) {
            replace(dataList.indexOf(oldData), newData);
        }
    }

    @Override
    public void insert(int index, T data) {
        if (dataList != null) {
            dataList.add(index, data);
            notifyItemInserted(index);
        }
    }

    @Override
    public void remove(int index) {
        if (dataList != null) {
            dataList.remove(index);
            notifyItemRemoved(index);
        }
    }

    @Override
    public void remove(T data) {
        if (dataList != null) {
            dataList.remove(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeHead() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(0);
            notifyItemRemoved(0);
        }
    }

    @Override
    public void removeTail() {
        if (dataList != null && dataList.size() > 0) {
            dataList.remove(dataList.size() - 1);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeAll(List<T> datas) {
        if (dataList != null) {
            dataList.removeAll(datas);
            notifyDataSetChanged();
        }
    }
}

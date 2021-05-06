package com.ybs.base_module.base.adapter.more

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ybs.distributenettool.base.MyApp


/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.adapter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/16 9:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/16 9:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */



abstract class BaseMultiRvAdpt<D: ITypeData>(
    val ctx: Context = MyApp.app,
    var dataList: MutableList<D> = mutableListOf(),
        ) : RecyclerView.Adapter<BaseMultiRvHolder>(),
    View.OnClickListener,
    View.OnLongClickListener,
    IDataHelper<D> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getVh(parent, viewType)

   protected open fun getVh(
        parent: ViewGroup,
        viewType: Int
    ) = BaseMultiRvHolder(getBinding(parent, viewType))

    protected abstract fun getBinding(parent: ViewGroup, viewType: Int): ViewBinding

    override fun onBindViewHolder(holder: BaseMultiRvHolder, position: Int) {
        val data = dataList[position]
        onDataBind(holder, data, position, selectedPos == position)
    }

    protected abstract fun onDataBind(vh: BaseMultiRvHolder, data: D, pos: Int, isSelected: Boolean)


    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int)=dataList[position].getType()

    override fun onClick(v: View) {

    }

    override fun onLongClick(v: View) = false

    var selectedPos = -1

    open fun updateSelectedPos(indexOf: Int) {
        selectedPos = indexOf
        notifyDataSetChanged()
    }

    open fun isOriginalPos(): Boolean {
        return selectedPos == -1
    }


    override fun updateAll(newDatas: MutableList<D>) {
        dataList = newDatas
        notifyDataSetChanged()
    }

    override fun addAll(newDatas: MutableList<D>) {
        dataList.addAll(newDatas)
        notifyDataSetChanged()
    }

    override fun clear() {
        selectedPos = -1
        dataList.clear()
        notifyDataSetChanged()
    }

    override fun addHead(data: D) {
        dataList.add(0, data)
        notifyItemInserted(0)
    }

    override fun append(data: D) {
        dataList.add(data)
        notifyItemInserted(itemCount - 1)
    }

    override fun replace(index: Int, data: D) {
        dataList.add(index, data)
        dataList.removeAt(index + 1)
        notifyItemRangeChanged(index, 2)
    }

    override fun replace(oldData: D, newData: D) {
        replace(dataList.indexOf(oldData), newData)
    }

    override fun insert(index: Int, data: D) {
        dataList.add(index, data)
        notifyItemInserted(index)
    }

    override fun remove(index: Int) {
        dataList.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun remove(data: D) {
        dataList.remove(data)
        notifyDataSetChanged()
    }

    override fun removeHead() {
        dataList.removeAt(0)
        notifyItemRemoved(0)
    }

    override fun removeTail() {
        dataList.removeAt(dataList.size - 1)
        notifyDataSetChanged()
    }

    override fun removeAll(datas: MutableList<D>) {
        dataList.removeAll(datas)
        notifyDataSetChanged()
    }
}
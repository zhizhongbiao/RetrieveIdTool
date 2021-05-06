package com.ybs.base_module.base.adapter.more

/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */
interface IDataHelper<D> {

    fun updateAll(newData: MutableList<D>)
    fun addAll(newData: MutableList<D>)
    fun clear()
    fun addHead(data: D)
    fun append(data: D)
    fun replace(index: Int, data: D)
    fun replace(oldData: D, newData: D)
    fun insert(index: Int, data: D)
    fun remove(index: Int)
    fun remove(data: D)
    fun removeHead()
    fun removeTail()
    fun removeAll(data: MutableList<D>)
}
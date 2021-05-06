package com.ybs.base_module.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ybs.base_module.base.http.response.Resource
import com.ybs.base_module.base.http.response.Response
import e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.vm
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 20:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 20:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
open class BaseVm() : ViewModel() {


    protected val requestCounter = MutableLiveData<Int>()

    private fun incCounter() {
        requestCounter?.apply {
            postValue(if (value == null) 1 else value!!.inc())
        }
    }

    private fun decCounter() {
        requestCounter?.apply {
            if (value == 0) return@apply
            postValue(value!!.dec())
        }
    }

    protected fun <D> baseRequest(tip: String = "正在请求数据", block: suspend () -> Response<D>) =
        liveData(Dispatchers.IO) {
            incCounter()
            emit(Resource.loading(tip))
            try {
                val data = block()
                if (data.isOk()) {
                    emit(Resource.success(data.payload, data.msg))
                    e("data.isOk()  data.msg = ${data.msg}")
                } else {
                    emit(Resource.error(data.msg))
                    e("data.isNotOk()  data.msg = ${data.msg}")
                }
            } catch (ex: Exception) {
                e("exp = ${ex.message}")
                emit(Resource.error(ex.message ?: "",data = null))
            } finally {
                decCounter()
            }
        }


  protected  fun <D> basePost(
        dataCb: MutableLiveData<Resource<D>>,
        loadingMsg: String = "正在获取数据",
        block: suspend () -> Response<D>
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataCb.postValue(Resource.loading(loadingMsg))
        try {
            val response = block()
            if (response.isOk()) {
                dataCb.postValue(Resource.success(response.payload))
            } else {
                dataCb.postValue(Resource.error(response.msg))
            }
        } catch (e: Exception) {
            e("exp = ${e.message}")
            dataCb.postValue(Resource.error(e.message))
        } finally {
            decCounter()
        }
    }

}
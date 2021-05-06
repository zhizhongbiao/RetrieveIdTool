package com.ybs.base_module.base.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 19:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 19:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


abstract class BaseVmAct<B : ViewBinding> : BaseAct<B>() {


    fun <VM : ViewModel> retrieveVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(this, factory).get(vmClz)
}
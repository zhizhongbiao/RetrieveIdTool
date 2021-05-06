package com.ybs.distributenettool.base.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 20:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 20:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseVmFg<B : ViewBinding> : BaseFg<B>() {


    fun <VM : ViewModel> retrieveVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(this, factory).get(vmClz)

    fun <VM : ViewModel> retrieveActVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(requireActivity(), factory).get(vmClz)

}
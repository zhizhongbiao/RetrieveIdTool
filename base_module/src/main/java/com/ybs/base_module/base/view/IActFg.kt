package com.ybs.distributenettool.base.view

import androidx.fragment.app.FragmentActivity

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/14 11:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/14 11:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface IActFg<A : FragmentActivity> {

    fun getAct(): A

}
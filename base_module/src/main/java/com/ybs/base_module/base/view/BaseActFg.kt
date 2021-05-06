package com.ybs.distributenettool.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/13 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/13 17:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
abstract class BaseActFg<B : ViewBinding, A : FragmentActivity> : Fragment(), IActFg<A> {

    var binding: B? = null

    override fun getAct() = requireActivity() as A

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getLayoutBinding(inflater, container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()


    abstract fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?): B?


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}
package com.ybs.base_module.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.adapter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/16 9:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/16 9:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class BaseRvHolder<BD : ViewBinding>(val binding: BD) : RecyclerView.ViewHolder(binding.root)
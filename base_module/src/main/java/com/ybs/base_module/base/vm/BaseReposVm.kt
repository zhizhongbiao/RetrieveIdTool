package com.ybs.base_module.base.vm

import com.ybs.base_module.base.http.request.BaseRepos

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
open class BaseReposVm<R: BaseRepos<*>>(protected val repos:R) : BaseVm(){

}
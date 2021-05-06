package com.ybs.base_module.base.http.request

import okhttp3.Interceptor

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base_module.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 17:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 17:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(chain.request())
}
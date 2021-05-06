package com.ybs.retrieveidtool

import com.ybs.base_module.base.http.request.BaseRepos

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.retrieveidtool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/21 14:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 14:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MainRepos : BaseRepos<MyService> {

    private constructor() : super((MyService::class.java))


    companion object {
        val inst = MainRepos()
    }

    suspend fun uploadId(
        param: RepUpload
    ) = requestSer.uploadId(
        param
    )


    suspend fun uploadIdRegister(
        param: RepUploadRegister
    ) = requestSer.uploadIdRegister(
        param
    )
}
package com.ybs.retrieveidtool

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.retrieveidtool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 21:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 21:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class RepUpload(
    val deviceTag: String,
    val signToken: String,
    val timestamp: String,
    val macAddr: String="",
    val productionModelSn: String="XB-FF-1",
    val assemblyTime: String=timestamp
)



data class RepUploadRegister(
    val snCode: String?,
    val macAddr: String?,
    val signToken: String,
    val timestamp: String,
    val logTag: String="zzb",
    val productionModelSn: String="XB-FF-1",
    val assemblyTime: String=timestamp
)
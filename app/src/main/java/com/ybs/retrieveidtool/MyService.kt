package com.ybs.retrieveidtool

import com.ybs.base_module.base.http.response.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.retrieveidtool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 19:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 19:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface MyService {


    @POST("/api/production/pub/deviceMassProduction")
    @FormUrlEncoded
    suspend fun uploadId(
        @Field("deviceTag") deviceTag: String,
        @Field("signToken") signToken: String,
        @Field("timestamp") timestamp: String ,
        @Field("assemblyTime") assemblyTime: String=timestamp ,
        @Field("productionModelSn") productionModelSn: String="XB-FF-1"
    ): Response<String>

    @POST("/api/production/pub/deviceMassProduction")
    suspend fun uploadId(
        @Body param: RepUpload): Response<String>

    @POST("/api/production/pub/deviceMassProductionRegister")
    suspend fun uploadIdRegister(
        @Body param: RepUploadRegister): Response<String>
}

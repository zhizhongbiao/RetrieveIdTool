package com.ybs.retrieveidtool

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base_module.base.util.SysUtil
import com.ybs.base_module.base.vm.BaseReposVm
import com.ybs.ndklib.JniUtils
import d

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.retrieveidtool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/21 10:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 10:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


class MainVm : BaseReposVm<MainRepos>(MainRepos.inst) {

    val deviceSn = MutableLiveData<String>()
    val deviceMacAddr = MutableLiveData<String>()


    companion object{
         var inst :MainVm?=null
    }

    init {
        inst=this
    }






    fun uploadSn(deviceTag: String) = baseRequest {
        val currentTimeMillis = System.currentTimeMillis().toString()
        val deviceToken = JniUtils.getDeviceToken(deviceTag, currentTimeMillis, "XB-FF-1")

        repos.uploadId( RepUpload(
            deviceTag=deviceTag,
            timestamp=currentTimeMillis,
            signToken=deviceToken
        ))
    }


    fun uploadIdRegister(snCode: String?,macAddr: String?) = baseRequest {
        val currentTimeMillis = System.currentTimeMillis().toString()
        val deviceToken = JniUtils.getDeviceToken(snCode, currentTimeMillis, "XB-FF-1")

        repos.uploadIdRegister( RepUploadRegister(
            snCode= snCode,
            macAddr= macAddr,
            timestamp=currentTimeMillis,
            signToken=deviceToken
        ))

    }

    fun handleText(result: String) {

        d("result = $result")
        if (!SysUtil.stringIsMac(result)) {
            ToastUtils.showLong("字符串为非法Mac地址！")
            return
        }
        deviceMacAddr.postValue(result)
    }

    override fun onCleared() {
        super.onCleared()
        inst=null
    }

}
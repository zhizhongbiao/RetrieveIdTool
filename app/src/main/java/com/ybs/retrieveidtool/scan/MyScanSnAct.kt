package com.ybs.retrieveidtool.scan

import androidx.lifecycle.MutableLiveData
import com.google.zxing.Result
import com.king.zxing.CaptureActivity

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.retrieveidtool
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/21 20:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 20:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


val result = MutableLiveData<Result>()

class MyScanSnAct : CaptureActivity() {

    override fun onScanResultCallback(r: Result?): Boolean {
//        v("result = $r")
        r?.apply {
            result.postValue(r)
            finish()
        }

        return true
    }
}
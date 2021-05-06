package com.ybs.base_module.base.http

import com.ybs.base_module.base.util.getStr
import com.ybs.base_module.base.util.saveString

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base_module.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/22 15:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 15:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */



const val URL_TEST="https://ybstest.qxfoodom.com/"
const val URL="https://ybs.qxfoodom.com/"
const val KEY_URL="keyUrl"



fun getUrl()= getStr(KEY_URL, URL_TEST)

fun saveUrl(url:String){
    saveString(KEY_URL,url)
}
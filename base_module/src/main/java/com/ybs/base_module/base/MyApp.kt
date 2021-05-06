package com.ybs.distributenettool.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.ybs.base_module.BuildConfig

import d

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


class MyApp : Application() {

    companion object {
        lateinit var app: MyApp
    }

    override fun onCreate() {
        app = this
        super.onCreate()

        if (BuildConfig.DEBUG) {
            //初始化阿,必须要在ARouter.init()之前配置这两个方法才有效
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        d("Application init     ARouter.init  ")
    }


    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}
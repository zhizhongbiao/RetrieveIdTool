package com.ybs.base_module.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import pub.devrel.easypermissions.EasyPermissions

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

abstract class BaseAct<B : ViewBinding> : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    protected lateinit var binding: B

    protected var router: ARouter? = null

    abstract fun getLayoutBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getLayoutBinding()
        setContentView(binding.root)
        router = ARouter.getInstance()
        router?.inject(this)
        initView()
        requestPermission()
    }

    private fun requestPermission() {

        if (getPermissionArray().isEmpty()) return

        if (EasyPermissions.hasPermissions(
                this,
                *getPermissionArray()
            )) {
            onHadPermission()

        } else {
            EasyPermissions.requestPermissions(
                this, "本应用需要所有这些权限，缺一不可！",
                getRequestPermissionCode(),
                *getPermissionArray()
            )
        }
    }

    protected val defaultCode = 999

    open fun getRequestPermissionCode() = defaultCode

    protected open fun onHadPermission() {

    }

    protected open fun getPermissionArray(): Array<String> = emptyArray()

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        ToastUtils.showLong("权限已授予！")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        ToastUtils.showLong("权限已拒绝！")
    }

    abstract fun initView()


    override fun onDestroy() {
        router = null
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    fun getActTag() = this.javaClass.canonicalName

}
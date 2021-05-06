package com.ybs.retrieveidtool

import android.Manifest
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base_module.base.http.URL
import com.ybs.base_module.base.http.URL_TEST
import com.ybs.base_module.base.http.getUrl
import com.ybs.base_module.base.http.response.Status
import com.ybs.base_module.base.http.saveUrl
import com.ybs.base_module.base.util.SysUtil
import com.ybs.base_module.base.view.BaseVmAct
import com.ybs.distributenettool.base.view.loading.*
import com.ybs.retrieveidtool.databinding.ActivityMainBinding
import com.ybs.retrieveidtool.ocr.MainActivity2
import com.ybs.retrieveidtool.scan.MyScanSnAct
import com.ybs.retrieveidtool.scan.result
import d
import e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MainActivity : BaseVmAct<ActivityMainBinding>() {


    private val vm by lazy {
        retrieveVm(MainVm::class.java)
    }

    private val dlg by lazy {
        LoadingFg()
    }


    override fun getPermissionArray() = arrayOf(
        Manifest.permission.READ_PHONE_STATE
    )

    override fun getLayoutBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        binding.btnSwitchUrl.text = if (getUrl() == URL_TEST) "测试环境" else "生产环境"

        vm.deviceSn.observe(this, Observer {
            binding.tvSn.text = "S/N = $it"
        })
        vm.deviceMacAddr.observe(this, Observer {
            binding.tvMacAddr.text = "MacAddr = $it"
        })

        result.observe(this, Observer {
            e("result = $it")
            vm.deviceSn.postValue(it.text)
        })

    }

    fun getSn(view: View) {
        val serialNumber = SysUtil.getSerialNumber(this)
        vm.deviceSn.value = serialNumber
        d("getSn")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        super.onPermissionsDenied(requestCode, perms)
        Toast.makeText(this, "请赋予所有要求的权限！", Toast.LENGTH_LONG).show()
    }

    fun scanSn(view: View) {
        //跳转的默认扫码界面
        startActivityForResult(Intent(this, MyScanSnAct::class.java), 88)
    }


    fun getMac(view: View) {
        val mac = SysUtil.getMacAddress(this)
        vm.deviceMacAddr.value = mac
        d("getMac")
    }

    fun uploadDevSn(view: View) {
        val sn = vm.deviceSn.value

        if (TextUtils.isEmpty(sn) ) {
            ToastUtils.showLong("Sn 不能为空！")
            return
        }

        vm.uploadSn(sn!!).observe(this@MainActivity, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_LOADING, "上传SN中...")).showDlg(
                        supportFragmentManager
                    )
                }
                Status.SUCCESS -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_SUCCEEDED, "上传SN成功"))
                }
                Status.ERROR -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_FAILED, "上传SN失败"))
                }
            }
        })
    }

    fun getMacAndSn(view: View) {
        getMac(view)
        getSn(view)
    }

    fun switchUrl(view: View) {
        saveUrl(if (getUrl() == URL_TEST) URL else URL_TEST)
        ToastUtils.showLong("2秒后将退出应用，请重新启动！")

        lifecycleScope.launch(Dispatchers.IO)
        {
            delay(2000)
            exitProcess(0)
        }
    }

    fun scanMac(view: View) {

//        startActivityForResult(Intent(this, MyScanMacAct::class.java), 55)
        startActivityForResult(Intent(this, MainActivity2::class.java), 55)
    }

    fun uploadDevMac(view: View) {

        val mac = vm.deviceMacAddr.value

        if (TextUtils.isEmpty(mac) ) {
            ToastUtils.showLong(" mac 不能为空！")
            return
        }

        vm.uploadIdRegister(mac,mac).observe(this@MainActivity, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_LOADING, "上传Mac中...")).showDlg(
                        supportFragmentManager
                    )
                }
                Status.SUCCESS -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_SUCCEEDED, "上传Mac成功"))
                }
                Status.ERROR -> {
                    dlg.setUIState(DlgStateBean(DLG_LOAD_FAILED, "上传Mac失败"))
                }
            }
        })

    }




}
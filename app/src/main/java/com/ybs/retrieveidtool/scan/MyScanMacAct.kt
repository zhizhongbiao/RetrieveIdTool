package com.ybs.retrieveidtool.scan

import android.Manifest

import com.shouzhong.scanner.Result
import com.ybs.base_module.base.view.BaseVmAct
import com.ybs.retrieveidtool.databinding.ActScanMacBinding
import e


class MyScanMacAct : BaseVmAct<ActScanMacBinding>() {

    override fun getPermissionArray() = arrayOf(
        Manifest.permission.READ_PHONE_STATE
    )

    override fun getLayoutBinding() = ActScanMacBinding.inflate(layoutInflater)

    override fun initView() {

        binding.sv.setViewFinder(ViewFinder(this))
        binding.sv.setSaveBmp(true)
        binding.sv.setEnableZXing(true)
        binding.sv.setEnableZBar(true)
        binding.sv.setEnableBankCard(true)
        binding.sv.setEnableIdCard(true)

        binding.sv.setEnableLicensePlate(true)
        binding.sv.setCallback { result: Result ->
            e("result $result")
            binding.sv.restartPreviewAfterDelay(2000)
        }

    }

    override fun onResume() {
        super.onResume()
        binding.sv.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.sv.onPause()
    }


}
package com.ybs.distributenettool.base.view

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


/**
 *
 * @ProjectName:    AW
 * @Package:        com.aylson.aw.view.locate
 * @ClassName:      LocateFg
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/6/12   10:06
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/6/12   10:06
 * @UpdateRemark:
 * @Version:        1.0
 */
abstract class BaseDialogFg<B : ViewBinding> : DialogFragment(), DialogInterface.OnShowListener,
    DialogInterface.OnKeyListener {

    var binding: B? = null

    abstract fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?): B?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getLayoutBinding(inflater, container)

        //设置背景透明
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //关闭点击外面消失的效果
        dialog?.setCanceledOnTouchOutside(true)
        //去掉低版本部分机型顶部出现一条蓝线的现象
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //设置监听对话框显示
        dialog?.setOnShowListener(this)
        //监听显示Dialog时的键盘事件
        dialog?.setOnKeyListener(this)

        //window背后所以变灰暗
        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_DIM_BEHIND,
            WindowManager.LayoutParams.FLAG_DIM_BEHIND
        )
        dialog?.window?.setDimAmount(0.7f)


        //不要焦点，保持全屏
//        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
//         dialog?. window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        fullScreenImmersive(dialog?.window?.decorView!!)
//         dialog?. window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        return binding?.root
    }

    abstract fun initView(args: Bundle?)


    private fun fullScreenImmersive(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
            view.systemUiVisibility = uiOptions
            view.setOnSystemUiVisibilityChangeListener {
                var uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or  //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or  //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN or  //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                uiOptions = uiOptions or 0x00001000
                view.systemUiVisibility = uiOptions
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener(this)
        initView(arguments)
    }


    fun <VM : ViewModel> retrieveVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(this, factory).get(vmClz)

    fun <VM : ViewModel> retrieveActVm(
        vmClz: Class<VM>,
        factory: ViewModelProvider.Factory = ViewModelProvider.NewInstanceFactory()
    ) = ViewModelProvider(requireActivity(), factory).get(vmClz)


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onShow(d: DialogInterface?) {
        val params = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?) = false


    fun getFgTag() = this.javaClass.canonicalName


}
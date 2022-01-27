package com.holderzone.library.composescaffold.base.dialog

import android.os.Bundle
import android.view.WindowManager
import com.alibaba.android.arouter.launcher.ARouter
import com.holderzone.library.composescaffold.util.HolderSoftKeyboardUtils


/**
 * @ClassName : AbsBaseDialogFragment
 * @Description:
 * @Author: XiaRuPeng
 * @Date: 2021/7/23 11:16
 */

abstract class HolderDialogFragment :BaseDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //HolderSoftKeyboardUtils.setHideNavKeyChanagListener(requireActivity())
        // 在该生命周期执行ARouter的注入，原因如下：
        // Fragment会存在只重建视图，不重建实例的情况。这种情况下，ARouter的注入不需要再次执行，故放在onCreate之后，onCreateView之前
        ARouter.getInstance().inject(this)
    }

    override fun onStart() {
        super.onStart()
//        HolderSoftKeyboardUtils.hideNavKey(requireActivity())
//        dialog?.let { HolderSoftKeyboardUtils.hideNavKeyDialog(it) }

    }

    override fun onStop() {
        super.onStop()
//        HolderSoftKeyboardUtils.hideNavKey(requireActivity())
//        dialog?.let { HolderSoftKeyboardUtils.hideNavKeyDialog(it) }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.apply {
            //HolderSoftKeyboardUtils.setDialogHideNavKeyChanagListener(this)
            // 默认不弹出软键盘
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

            // 注册触摸事件，实现点击软键盘区域外，隐藏软键盘的效果
            window?.decorView?.setOnTouchListener { _, event ->
                HolderSoftKeyboardUtils.dispatchTouchEvent(requireActivity(), event)
            }

//            HolderSoftKeyboardUtils.hideNavKeyDialog(this)
        }

//        HolderSoftKeyboardUtils.hideNavKey(requireActivity())

        initView(savedInstanceState)

        initData(savedInstanceState)
    }

    /**
     * 初始化 View
     *
     * @param savedInstanceState
     * @return
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    abstract fun initData(savedInstanceState: Bundle?)

}
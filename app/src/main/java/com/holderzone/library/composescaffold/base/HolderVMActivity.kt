package com.holderzone.library.composescaffold.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.holderzone.library.composescaffold.base.mvvm.BaseViewModel
import com.holderzone.library.composescaffold.base.mvvm.BaseViewModelActivity

import com.holderzone.library.composescaffold.util.LoadingDialog


/**
 * @ClassName : HolderBaseVMVBActivity
 * @Description: 项目级 baseactivity 主要针对项目做特殊处理
 * @Author: WuZhuoyu
 * @Date: 2021/7/23 10:04
 */

abstract class HolderVMActivity<VM : BaseViewModel>: BaseViewModelActivity<VM>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        injection()
        super.onCreate(savedInstanceState)

        mLoadingDialog = LoadingDialog(this)


        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
    }

    /**
     * 三方框架注入
     */
    protected override fun injection() {
        ARouter.getInstance().inject(this)
    }


    override fun showLoadingUI(isShow: Boolean) {
        mLoadingDialog?.showDialog(this, isShow)
    }

    override fun onPause() {
        super.onPause()
        mLoadingDialog?.showDialog(this, false)
    }

    override fun showEmptyUI(isShow: Boolean) {
        //Todo:UI实现
    }

    override fun showErrorNetworkUI(isShow: Boolean) {
        //Todo:UI实现
    }

    override fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }


    /**
     * 隐藏键盘输入
     * */
    protected fun hideSoftInput() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            window.decorView.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    /**
     * 显示键盘输入
     * */
    protected fun showSoftInput(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoadingDialog?.dismiss()
    }
}
package com.holderzone.library.composescaffold.base.mvvm

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.holderzone.library.composescaffold.util.LoadingDialog
import com.holderzone.library.composescaffold.base.impl.IViewBehaviour


/**
 * @ClassName : BaseActivity
 * @Description:
 *
 * feature: initLayoutId模板方法
 *
 * feature: initView模板方法
 *
 * feature: initData模板方法
 *
 * feature: initListener模板方法
 *
 * @Author: WuZhuoyu
 * @Date: 2021/3/5 11:15
 */

abstract class BaseActivity : AppCompatActivity(), IViewBehaviour {


     var mLoadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏

        mLoadingDialog = LoadingDialog(this)

        injection()

        initContentView(savedInstanceState)
        initData(savedInstanceState)
    }

    /**
     * 三方框架注入
     */
    protected open fun injection() {
        ARouter.getInstance().inject(this)
    }

    protected open fun initContentView(savedInstanceState: Bundle?) {

    }

    /**============================================================
     *  抽象方法
     **===========================================================*/


    /**
     * 初始化数据
     * @param savedInstanceState
     */
    protected abstract fun initData(savedInstanceState: Bundle?)

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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
package com.holderzone.library.composescaffold.base.mvvm

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import com.holderzone.library.composescaffold.util.ext.observeNonNull
import com.holderzone.library.composescaffold.base.impl.IViewBehaviour
import com.holderzone.library.composescaffold.util.ActivityManagerUtils
import com.holderzone.library.composescaffold.util.DisplayUtil


/**
 * @ClassName : BaseViewModelActivity
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/3/9 10:42
 */

abstract class BaseViewModelActivity<VM : BaseViewModel> : BaseActivity(), IViewBehaviour{

    private lateinit var _viewModel:VM

    protected val viewModel get() =_viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //屏蔽手机系统显示大小设置
        DisplayUtil.setDefaultDisplay(this)
    }

    override fun initContentView(savedInstanceState: Bundle?) {
        setViewModel(injectViewModel())
        ActivityManagerUtils.addActivity(this)
        initInternalObserver()
    }

    protected abstract fun injectViewModel():VM

    private fun setViewModel(viewModel: VM) {
        _viewModel = viewModel
        _viewModel.application = application
    }


    /**
     * 初始化观察者
     * */
    protected open fun initInternalObserver() {
        viewModel.loadingEvent.observeNonNull(this) {
            showLoadingUI(it)
        }
        viewModel.emptyPageEvent.observeNonNull(this) {
            showEmptyUI(it)
        }
        viewModel.toastEvent.observeNonNull(this) {
            showToast(it)
        }
    }

    override fun onPause() {
        super.onPause()
        mLoadingDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManagerUtils.removeActivity(this)
    }

    /**
     *  屏蔽手机系统字体大小设置
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.fontScale != 1f) {
            //非默认值
            resources
        }
        super.onConfigurationChanged(newConfig)
    }

    /**
     *  屏蔽手机系统字体大小设置
     */
    override fun getResources(): Resources {
        val res: Resources = super.getResources()
        if (res.configuration.fontScale != 1f) { //非默认值
            val newConfig = Configuration()
            newConfig.setToDefaults() //设置默认
            res.updateConfiguration(newConfig, res.displayMetrics)
        }
        return res
    }
}
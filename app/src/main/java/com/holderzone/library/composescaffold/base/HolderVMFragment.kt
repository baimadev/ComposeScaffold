package com.holderzone.library.composescaffold.base

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.holderzone.library.composescaffold.base.mvvm.BaseViewModel
import com.holderzone.library.composescaffold.base.mvvm.BaseViewModelFragment
import com.holderzone.library.composescaffold.util.LoadingDialog


/**
 * @ClassName : HolderBaseVMVBFragment
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/7/23 10:52
 */

abstract class HolderVMFragment<VM: BaseViewModel>: BaseViewModelFragment<VM>(){
    private var mLoadingDialog: LoadingDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        injection()
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLoadingDialog = LoadingDialog(requireContext())
    }

    /**
     * 三方框架注入
     */
    protected open fun injection() {
        ARouter.getInstance().inject(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        mLoadingDialog?.showDialog(requireContext(),false)
    }
}
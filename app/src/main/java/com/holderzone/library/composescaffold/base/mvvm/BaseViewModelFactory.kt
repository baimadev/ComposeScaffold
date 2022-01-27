package com.holderzone.library.composescaffold.base.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @ClassName : BaseViewModelFactory
 * @Description:创建ViewModel的工厂，以此方法创建的ViewModel，可在构造函数中传参
 * @Author: WuZhuoyu
 * @Date: 2021/3/10 17:23
 */
open class BaseViewModelFactory constructor( val viewModel: BaseViewModel) :ViewModelProvider.Factory {

    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        try {
            return viewModel as VM
        }catch (e:InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e:IllegalAccessException) {
            throw RuntimeException("Illegal access of $modelClass", e)
        }
    }
}
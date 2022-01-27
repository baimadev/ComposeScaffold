package com.holderzone.library.composescaffold.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment


/**
 * @ClassName : BaseViewModelFragment
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/3/15 18:06
 */

abstract class BaseViewModelFragment<VM : BaseViewModel> : Fragment() {

    private lateinit var _viewModel: VM

    protected val viewModel get() = _viewModel

    /**
     * 绑定ViewModel
     *
     * */
    protected abstract fun injectViewModel(): VM

    @Composable
    abstract fun content()

    abstract fun initData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setViewModel(injectViewModel())
        initData()
        return ComposeView(requireContext()).apply {
            setContent {
                content()
            }
        }
    }

    private fun setViewModel(viewModel: VM) {
        _viewModel = viewModel
        _viewModel.application = requireActivity().application
    }

}
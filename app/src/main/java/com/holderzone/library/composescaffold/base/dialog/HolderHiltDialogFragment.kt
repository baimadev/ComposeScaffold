package com.holderzone.library.composescaffold.base.dialog

import android.os.Bundle
import androidx.lifecycle.ViewModel


/**
 * @ClassName : AbsHiltDialogFragment
 * @Description:
 * @Author: XiaRuPeng
 * @Date: 2021/7/23 11:21
 */


abstract class HolderHiltDialogFragment <VM:ViewModel>: HolderDialogFragment() {

    lateinit var viewModel: VM

    abstract fun injectViewModel():VM

    override fun initData(savedInstanceState: Bundle?) {
        viewModel = injectViewModel()
    }

////    <editor-fold desc="DEMO示例" defaultstate="collapsed">
//
//
//    object DialogFactory {
//        fun testDialog(): BaseDialogFragment {
//            val dialog = ARouter.getInstance().build(SnackRouterPathContract.SnackDialogTest)
//                .navigation() as TestHiltDialogFragment
//            dialog.sex = "男"
//            return dialog
//        }
//    }
//
//
//@AndroidEntryPoint
//@Route(path = SnackRouterPathContract.SnackDialogTest)
//class TestHiltDialogFragment : HolderHiltDialogFragment<SnackViewModel>() {
//
//    //使用Module注入
//    @Inject
//    lateinit var testData:TestDialog
//
//
//    var sex:String = ""
//
//    override var compileOverrideOptions: (DialogOptions.() -> Unit)? = {
//        layoutId = R.layout.dialog_test
//        width = UtilsExtension.dp2px(resources,450f)
//        height = UtilsExtension.dp2px(resources,450f)
//        touchCancel = false
//        outCancel = false
//        setDialogTheme {
//            R.style.AccentBuleFullScreenDialogRadius
//        }
//    }
//
//
//    override fun initView(savedInstanceState: Bundle?) {
//
//
//    }
//
//    override fun initData(savedInstanceState: Bundle?) {
//        super.initData(savedInstanceState)
//        Logger.e(viewModel.toString())
//        Logger.e(testData.str)
//        Logger.e(sex)
//    }
//
//    override fun injectViewModel(): SnackViewModel {
//        return createViewModel()
//    }
//
//
//}
////</editor-fold>
}

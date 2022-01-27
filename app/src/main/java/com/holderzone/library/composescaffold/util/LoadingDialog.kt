package com.holderzone.library.composescaffold.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.holderzone.library.composescaffold.R

class LoadingDialog(context: Context) : Dialog(
    context,
    R.style.LoadingDialog
) {

    init {
        setContentView(R.layout.loading_dialog)
    }

    fun showDialog(context: Context, isShow: Boolean) {
        //activity或者fragment销毁时return
        if (context is Activity) {
            if (context.isFinishing) return
        }else if(context is Fragment){
            if (context.isDetached) return
        }
        dismiss()
        if (isShow) {
            show()
        } else {
            dismiss()
        }

    }
}
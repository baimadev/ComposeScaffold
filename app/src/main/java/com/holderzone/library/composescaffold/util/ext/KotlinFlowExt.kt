package com.holderzone.library.composescaffold.util.ext

import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


/**
 * @ClassName : KotlinFlowExt
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/6/11 12:16
 */

fun <T> Flow<T>.catchLog(): Flow<T> {
    return this.catch {
        Log.e("Flow Catch","error : ${it.message ?: it.toString()}")
    }
}

fun <T> Flow<T>.catchToast(): Flow<T> {
    return this.catch {
        ToastUtils.showLong("error : ${it.message ?:it.toString()}")
    }
}
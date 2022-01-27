package com.holderzone.library.composescaffold.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @ClassName : ObserverExts
 * @Description:
 * @Author: WuZhuoyu
 * @Date: 2021/3/10 17:51
 */

/**
 * 简化LiveData的订阅操作，参数可为null
 */
inline fun <T> LiveData<T>.observeNullable(owner: LifecycleOwner, crossinline block: (T) -> Unit) {
    this.observe(owner, Observer {
        block(it)
    })
}

/**
 * 简化LiveData的订阅操作，参数不为null
 */
inline fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, crossinline block: (T) -> Unit) {
    this.observe(owner, Observer {
        if (it != null) {
            block(it)
        }
    })
}

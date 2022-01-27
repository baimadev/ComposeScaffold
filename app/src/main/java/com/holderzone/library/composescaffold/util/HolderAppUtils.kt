package com.holderzone.library.composescaffold.util

import com.blankj.utilcode.util.AppUtils

/**
 * 应用工具类
 * @author terry
 * @date 2018/08/25 下午19:58
 */
object HolderAppUtils {

    fun getAppName(): String = AppUtils.getAppName()

    fun getVerCode(): Int = AppUtils.getAppVersionCode()

    fun getVerName(): String = AppUtils.getAppVersionName()

    fun isAppDebug(): Boolean = AppUtils.isAppDebug()

    fun relaunchApp(): Unit = AppUtils.relaunchApp()
}
package com.holderzone.library.composescaffold.util

import com.blankj.utilcode.util.ConvertUtils

/**
 * 转换工具类
 * @author terry
 * @date 2018/08/22 下午11:59
 */
object HolderConvertUtls {

    fun dp2px(dpValue: Float): Int = ConvertUtils.dp2px(dpValue)

    fun px2dp(pxValue: Float): Int = ConvertUtils.px2dp(pxValue)

    fun sp2px(dpValue: Float): Int = ConvertUtils.sp2px(dpValue)

    fun px2sp(pxValue: Float): Int = ConvertUtils.px2sp(pxValue)
}
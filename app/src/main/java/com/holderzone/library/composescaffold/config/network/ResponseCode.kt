package com.holderzone.library.composescaffold.config.network

object ResponseCode {
    /**
     * 运营大后台基础数据结构中 0 成功 1 失败
     */
    const val SUCCESS = 0

    const val UNAUTHORIZED = 401

    const val SERVER_UNUSUAL = 801
    /**
     * 登录已过期code
     */
    const val LOGIN_EXPIRED = -999

    /**
     * 设备不存在
     */
    const val DEVICE_UNEXIST = 10017
}
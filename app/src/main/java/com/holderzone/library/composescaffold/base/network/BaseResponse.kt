package com.holderzone.library.composescaffold.base.network

import com.example.customer.base.network.NetworkException
import com.holderzone.library.composescaffold.config.network.ResponseCode
import com.orhanobut.logger.Logger
import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "data")
    var data: T?,

    @Json(name = "returnCode")
    var code: Int = -1,

    @Json(name = "returnMessage")
    var msg: String = "",

    @Json(name = "obj")
    val obj: Any? = null,

    @Json(name = "dataList")
    val dataList: Any? =null,
) : Response<T> {
    override fun isSuccessful(): Boolean = code == ResponseCode.SUCCESS

    override fun getMessage(): String = msg

    override fun getResponseData(): T? = data
}

fun <T> BaseResponse<T>.getResult(): T {
    if (!isSuccessful() || getResponseData() == null) {
        when(code){
            ResponseCode.UNAUTHORIZED->{
                Logger.e("认证失败：$code ${getMessage()} ")
                throw NetworkException(code,getMessage())
            }
            ResponseCode.LOGIN_EXPIRED ->{
                Logger.e("登录已过期：$code ${getMessage()} ")
                throw NetworkException(code,getMessage())
            }
            ResponseCode.SERVER_UNUSUAL->{
                Logger.e("请求错误：$code ${getMessage()} ")
                throw NetworkException(code,getMessage())
            }
            else ->{
                Logger.e("网络请求失败：$code ${getMessage()} ")
                throw NetworkException(code,getMessage())
            }
        }
    }
    return getResponseData()!!
}

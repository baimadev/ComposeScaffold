package com.holderzone.library.composescaffold.base.network

import com.example.customer.base.network.NetworkException
import com.holderzone.library.composescaffold.config.network.ResponseCode
import com.orhanobut.logger.Logger
import com.squareup.moshi.Json

data class OdooResponse<T>(

    @Json(name = "result")
    var result: OdooResult<T>,

    @Json(name = "errorCode")
    var errorCode: Int? = null,


) : Response<T> {

    override fun isSuccessful(): Boolean = result.returnCode == ResponseCode.SUCCESS

    override fun getMessage(): String = result.returnMessage

    override fun getResponseData(): T? = result.data
}

data class OdooResult<T>(

    @Json(name = "returnCode")
    val returnCode :Int,

    @Json(name = "returnMessage")
    val returnMessage :String,

    @Json(name = "data")
    val data:T?,
)


fun <T> OdooResponse<T>.getResult(): T {

    if (!isSuccessful() || getResponseData() == null) {
//        when(errorCode){
//            ResponseCode.UNAUTHORIZED->{
//                Logger.e("认证失败：$errorCode ${getMessage()} ")
//                throw NetworkException(errorCode,getMessage())
//            }
//            ResponseCode.SERVER_UNUSUAL->{
//                Logger.e("请求错误：$errorCode ${getMessage()} ")
//                throw NetworkException(errorCode,getMessage())
//            }
//            else ->{
//                Logger.e("网络请求失败：$errorCode ${getMessage()} ")
//                throw NetworkException(errorCode,getMessage())
//            }
//        }
        throw NetworkException(errorCode?:result.returnCode,getMessage())
    }
    return getResponseData()!!
}
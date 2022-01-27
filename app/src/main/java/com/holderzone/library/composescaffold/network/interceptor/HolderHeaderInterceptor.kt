package com.holderzone.library.composescaffold.network.interceptor

import com.holderzone.library.composescaffold.config.network.HeaderConstant
import com.holderzone.library.composescaffold.config.EnvPrefKeys
import com.holderzone.library.composescaffold.util.XPrefs
import okhttp3.Interceptor
import okhttp3.Response

class HolderHeaderInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .headers(originalRequest.headers())
            .header("Accept", "application/json;charset=utf-8")
            .header("Content-type", "application/x-www-form-urlencoded")
            .method(originalRequest.method(), originalRequest.body())

        //token
        val token = XPrefs.getString(EnvPrefKeys.TOKEN_KEY)
        if (token != null) requestBuilder.header(HeaderConstant.HEADER_TOKEN, token)

//        //企业guid
//        val enterpriseGuid = XPrefs.getString(EnvPrefKeys.ENTERPRISE_GUID)
//        if (enterpriseGuid != null) requestBuilder.header(
//           HeaderConstant.ENTERPRISE_GUID,
//            enterpriseGuid
//        )
//
//        //运营主体guid
//        val operSubjectGuid = XPrefs.getString(EnvPrefKeys.MULTI_MEMBER_GUID)
//        if (operSubjectGuid != null) requestBuilder.header(
//            HeaderConstant.OPER_SUBJECT_GUID,
//            operSubjectGuid
//        )
//
//
//        //设备源
//        val source = XPrefs.getString(EnvPrefKeys.DEVICE_SOURCE_KEY)
//        if (source != null) requestBuilder.header(HeaderConstant.HEADER_SOURCE, source.toString())

        return chain.proceed(requestBuilder.build())
    }
}
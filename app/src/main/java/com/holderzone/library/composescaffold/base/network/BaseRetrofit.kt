package com.holderzone.library.composescaffold.base.network

import retrofit2.Retrofit

object BaseRetrofit {

    fun create(baseUrl:String,retrofitConfig: BaseRetrofitConfig):Retrofit{

       return Retrofit.Builder()
                .client(retrofitConfig.initOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(retrofitConfig.initConverterFactory())
                .build()
    }
}
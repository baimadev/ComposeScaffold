package com.example.customer.base.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

object BaseConverterFactory {

    fun create(): Converter.Factory {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        return MoshiConverterFactory.create(moshi)
    }
}
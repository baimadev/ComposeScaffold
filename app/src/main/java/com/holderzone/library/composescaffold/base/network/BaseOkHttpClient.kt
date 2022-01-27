package com.holderzone.library.composescaffold.base.network

import android.annotation.SuppressLint
import com.holderzone.library.composescaffold.BuildConfig
import com.holderzone.library.composescaffold.config.network.Settings
import com.holderzone.library.composescaffold.network.interceptor.LoggingInterceptor
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object BaseOkHttpClient {

    fun create(vararg interceptor: Interceptor): OkHttpClient {

        val builder = OkHttpClient.Builder()

        interceptor.forEach {
            builder.addInterceptor(it)
        }

        builder.connectTimeout(Settings.DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(Settings.DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(Settings.DEFAULT_READ_TIME, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logger = object : LoggingInterceptor.Logger {
                override fun log(message: String) {
                    Logger.t("OkHttp").d(message)
                }
            }
            val loggingInterceptor =  LoggingInterceptor(logger).apply { level = LoggingInterceptor.Level.BODY }
            builder.addInterceptor(loggingInterceptor)
        }

        // 信任所有Https证书。因为服务端是CA证书，肯定安全，所以直接信任就行了。
        builder.sslSocketFactory(sslSocketFactory(), trustManager())
        builder.hostnameVerifier(hostnameVerifier())

        return builder.build()
    }


    private fun sslSocketFactory(): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(trustManager())
        return SSLContext.getInstance("SSL").run {
            init(null, trustAllCerts, SecureRandom())
            socketFactory
        }
    }

    private fun trustManager() = object : X509TrustManager {

        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkClientTrusted(
                x509Certificates: Array<java.security.cert.X509Certificate>,
                s: String
        ) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class)
        override fun checkServerTrusted(
                x509Certificates: Array<java.security.cert.X509Certificate>,
                s: String
        ) {
        }

        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
    }

    private fun hostnameVerifier() = HostnameVerifier { _, _ -> true }
}
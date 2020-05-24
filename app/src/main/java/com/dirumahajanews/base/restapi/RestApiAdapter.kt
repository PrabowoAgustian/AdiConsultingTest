package com.dirumahajanews.base.restapi

import com.dirumahajanews.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestApiAdapter {
    private var logging: HttpLoggingInterceptor? = null

    fun createRestApi(): RestApi {
        return getRestAdapter(
            BuildConfig.apiURl
        ).create(
            RestApi::class.java
        )
    }

    private fun getRestAdapter(serverURL: String): Retrofit {
        val encryptionInterceptor = EncryptionInterceptor()
        val okHttpClientFactory = OkHttpClientFactory.getInstance()
        val logging = loggingLevel
        okHttpClientFactory.getOkHttpClientForRestAdapter()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(encryptionInterceptor)
        return Retrofit.Builder()
            .baseUrl(serverURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientFactory.getOkHttpClientForRestAdapter().build())
            .build()
    }

    private val loggingLevel: HttpLoggingInterceptor
        get() {
            if (logging == null) logging = HttpLoggingInterceptor()
            logging!!.level =
                HttpLoggingInterceptor.Level.BODY
            return logging!!
        }
}
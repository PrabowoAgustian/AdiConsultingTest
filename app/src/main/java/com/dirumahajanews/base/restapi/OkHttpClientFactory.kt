package com.dirumahajanews.base.restapi

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpClientFactory private constructor() {

    private val okHttpClientForRestAdapter = createHttpClientForRestAdapter()

    fun getOkHttpClientForRestAdapter(): OkHttpClient.Builder {
        return okHttpClientForRestAdapter
    }

    private fun createHttpClientForRestAdapter(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(
            TIMEOUT_CONNECTION_SECONDS,
            TimeUnit.SECONDS
        )
        httpClient.readTimeout(
            TIMEOUT_CONNECTION_SECONDS,
            TimeUnit.SECONDS
        )
        httpClient.connectionPool(
            ConnectionPool(
                MAX_REST_API_IDLE_CONNECTION,
                KEEP_ALIVE_IDLE_DURATION_MS,
                TimeUnit.SECONDS
            )
        )
        return httpClient
    }

    companion object {
        private const val TIMEOUT_CONNECTION_SECONDS: Long = 60
        private const val MAX_REST_API_IDLE_CONNECTION = 1
        private const val KEEP_ALIVE_IDLE_DURATION_MS: Long = 60000
        private var okHttpClientFactory: OkHttpClientFactory? = null

        fun getInstance(): OkHttpClientFactory {
            if (okHttpClientFactory == null) {
                okHttpClientFactory = OkHttpClientFactory()
            }
            return okHttpClientFactory!!
        }
    }
}
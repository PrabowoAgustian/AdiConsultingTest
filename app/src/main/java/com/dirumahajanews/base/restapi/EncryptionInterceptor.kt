package com.dirumahajanews.base.restapi

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class EncryptionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.body() != null)
            return chain.proceed(request)
        val token = request.header("Authorization")
        var newRequestUrl = request.url().toString()
        newRequestUrl = newRequestUrl.replace("%26", "&")
        newRequestUrl = newRequestUrl.replace("%3D", "=")
        newRequestUrl = newRequestUrl.replace("%2B", "+")
        newRequestUrl = newRequestUrl.replace("%2F", "/")
        newRequestUrl = newRequestUrl.replace("%2C", ",")
        newRequestUrl = newRequestUrl.replace("%3A", ":")
        val newRequest = Request.Builder()
            .url(newRequestUrl)
            .removeHeader("Authorization")
            .addHeader("Authorization", token ?: "")
            .build()
        return chain.proceed(newRequest)
    }
}
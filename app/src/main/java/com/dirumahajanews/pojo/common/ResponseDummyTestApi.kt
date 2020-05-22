package com.dirumahajanews.pojo.common

import android.annotation.SuppressLint

class ResponseDummyTestApi (
    private val status : String? = null,
    val totalResults : Int? = null,
    private val articles : Any? = null
) {
    @SuppressLint("DefaultLocale")
    fun getMessage(): String {
        return status?.toUpperCase() ?: ""
    }

    fun getArticles(): Any {
        return articles ?: "Null result"
    }
}
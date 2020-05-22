package com.dirumahajanews.pojo.common

import com.google.gson.annotations.SerializedName

class ErrorContent(
    val timestamp: Long = System.currentTimeMillis(),
    val status: Int = 0,
    val error: String = "",
    val exception: String = "",
    val message: String = "",
    val path: String = "",
    val result: String = "",
    @SerializedName("error_description") val errorDescription: String = ""
)
package com.dirumahajanews.pojo.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  NewsTopHeadline(
    var source: NewsSource,
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String? = null,
    var publishedAt: String = "",
    var content: String = "") : Parcelable
package com.dirumahajanews.pojo.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsSource (var id : String = "",
                       var name : String = "") : Parcelable
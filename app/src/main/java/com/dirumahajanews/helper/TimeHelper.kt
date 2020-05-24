package com.dirumahajanews.helper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class TimeHelper {
    companion object {

        fun getDateFormatedNew(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
            @SuppressLint("SimpleDateFormat") val outputFormat =
                SimpleDateFormat("EEE, dd MMM YYYY HH:mm")
            @SuppressLint("SimpleDateFormat") val otherInput =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            otherInput.timeZone = TimeZone.getTimeZone("UTC")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                try {
                    reformattedDate = outputFormat.format(otherInput.parse(time.replace("Z", "")))
                } catch (e1: ParseException) {
                    e1.printStackTrace()
                }

            }

            return reformattedDate
        }
    }
}
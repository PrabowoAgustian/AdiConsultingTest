package com.dirumahajanews.helper

class StringHelper {

    companion object {
        fun getStringBuilderToString(vararg items: String): String {
            val stringBuilder = StringBuilder()
            for (s in items) {
                stringBuilder.append(s)
            }
            return stringBuilder.toString()
        }
    }
}
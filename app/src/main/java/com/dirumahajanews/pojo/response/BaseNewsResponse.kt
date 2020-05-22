package com.dirumahajanews.pojo.response

data class BaseNewsResponse (var status : String = "",
                             var totalResults : Int = 0,
                             var articles : Any? = null)
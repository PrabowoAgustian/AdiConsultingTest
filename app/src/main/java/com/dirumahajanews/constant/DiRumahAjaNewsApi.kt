package com.dirumahajanews.constant

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class DiRumahAjaNewsApi {
    companion object {

        const val getListSourceNews = "v2/sources"
        const val getListTopHeadlineNews = "v2/top-headlines"
        const val getListEverythingNews = "v2/everything"
    }

}
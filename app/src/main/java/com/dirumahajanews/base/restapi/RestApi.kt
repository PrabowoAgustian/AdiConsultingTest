package com.dirumahajanews.base.restapi

import com.dirumahajanews.constant.DiRumahAjaNewsApi
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {

    @Headers("Content-Type: application/json")
    @GET(DiRumahAjaNewsApi.getListSourceNews)
    fun getListCategory(
        @Query("apiKey") apiKey: String
    ): Observable<Any>

    @Headers("Content-Type: application/json")
    @GET(DiRumahAjaNewsApi.getListTopHeadlineNews)
    fun getListTopHeadlineNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Observable<Any>

    @Headers("Content-Type: application/json")
    @GET(DiRumahAjaNewsApi.getListEverythingNews)
    fun getListEverythingNews(
        @Query("q") keyWord: String,
        @Query("apiKey") apiKey: String
    ): Observable<Any>
}
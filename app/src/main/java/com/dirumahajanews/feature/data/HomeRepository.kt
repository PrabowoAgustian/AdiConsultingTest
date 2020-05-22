package com.dirumahajanews.feature.data

import com.dirumahajanews.BuildConfig
import com.dirumahajanews.base.data.BaseRepository
import com.dirumahajanews.base.data.PreferenceManager
import com.dirumahajanews.base.restapi.RestApi
import io.reactivex.Observable
import javax.inject.Inject

class HomeRepository @Inject constructor(
    val restApi: RestApi,
    preferenceManager: PreferenceManager
) : BaseRepository(preferenceManager, restApi){

    fun getListCategory() : Observable<Any>{
        return restApi.getListCategory(BuildConfig.apiKey)
    }

    fun getListTopHeadlineNews(country : String) : Observable<Any>{
        return restApi.getListTopHeadlineNews(country, BuildConfig.apiKey)
    }
}
package com.dirumahajanews.feature.data

import com.dirumahajanews.BuildConfig
import com.dirumahajanews.base.data.BaseRepository
import com.dirumahajanews.base.data.PreferenceManager
import com.dirumahajanews.base.restapi.RestApi
import io.reactivex.Observable
import javax.inject.Inject

class SearchNewsRepository@Inject constructor(
    val restApi: RestApi,
    preferenceManager: PreferenceManager
) : BaseRepository(preferenceManager, restApi){

    fun getListEverythingNews(keyWord: String) : Observable<Any> {
        return restApi.getListEverythingNews(keyWord, BuildConfig.apiKey)
    }
}
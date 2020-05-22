package com.dirumahajanews.base.data

import com.dirumahajanews.base.restapi.RestApi

open class BaseRepository(private val preferenceManager: PreferenceManager) {

    private var restApi: RestApi? = null

    constructor(preferenceManager: PreferenceManager, restApi: RestApi) : this(preferenceManager) {
        this.restApi = restApi
    }
}
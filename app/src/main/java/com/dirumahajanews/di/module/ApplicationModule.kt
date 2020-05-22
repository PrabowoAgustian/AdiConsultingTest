package com.dirumahajanews.di.module

import android.content.Context
import com.dirumahajanews.DiRumahAjaApp
import com.dirumahajanews.base.restapi.RestApi
import com.dirumahajanews.base.restapi.RestApiAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Named("ApplicationContext")
    @Provides
    @Singleton
    fun provideContext(application: DiRumahAjaApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRestApi(): RestApi {
        return RestApiAdapter.createRestApi()
    }
}
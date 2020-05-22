package com.dirumahajanews.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class GsonModule {
    @Named("Gson")
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
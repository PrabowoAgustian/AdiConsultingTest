package com.dirumahajanews.di.module

import android.content.Context
import com.dirumahajanews.BuildConfig
import com.dirumahajanews.base.data.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class PreferenceModule {
    @Named("preferenceName")
    @Provides
    @Singleton
    fun providePreferenceName(): String {
        return BuildConfig.keyPreference
    }

    @Provides
    @Singleton
    fun providePreferenceManager(
        @Named("ApplicationContext") context: Context,
        @Named("preferenceName") preferenceName: String
    ): PreferenceManager {
        return PreferenceManager(
            context,
            preferenceName
        )
    }
}
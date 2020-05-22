package com.dirumahajanews.di.module

import com.dirumahajanews.utils.BaseSchedulerProvider
import com.dirumahajanews.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerFacadeModule {
    @Provides
    @Singleton
    fun provideSchedule(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}
package com.dirumahajanews.di.module

import com.dirumahajanews.feature.view.main.HomeActivity
import com.dirumahajanews.feature.view.searchpage.SearchNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun bindSearchNewsActivity(): SearchNewsActivity
}
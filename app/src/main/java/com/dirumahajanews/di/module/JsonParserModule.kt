package com.dirumahajanews.di.module

import com.google.gson.Gson
import com.dirumahajanews.utils.JsonParser
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class JsonParserModule {
    @Named("JsonParser")
    @Provides
    @Singleton
    fun provideJsonParser(@Named("Gson") gson: Gson?): JsonParser {
        return JsonParser(gson!!)
    }
}
package com.dirumahajanews.feature.domain

import com.dirumahajanews.base.domain.BaseUseCase
import com.dirumahajanews.feature.data.HomeRepository
import com.dirumahajanews.utils.BaseSchedulerProvider
import com.dirumahajanews.utils.JsonParser
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository,
    schedulerFacade: BaseSchedulerProvider,
    @Named("JsonParser") private val parser: JsonParser
) : BaseUseCase(repository,parser, schedulerFacade){

    fun getListCategory() : Observable<Any>{
        return repository.getListCategory()
            .observeOn(ui())
            .subscribeOn(io())
    }

    fun getListTopHeadlineNews(country : String) : Observable<Any>{
        return repository.getListTopHeadlineNews(country)
            .observeOn(ui())
            .subscribeOn(io())
    }
}
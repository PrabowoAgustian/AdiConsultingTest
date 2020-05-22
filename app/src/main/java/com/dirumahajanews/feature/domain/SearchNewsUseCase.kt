package com.dirumahajanews.feature.domain

import com.dirumahajanews.base.domain.BaseUseCase
import com.dirumahajanews.feature.data.SearchNewsRepository
import com.dirumahajanews.utils.BaseSchedulerProvider
import com.dirumahajanews.utils.JsonParser
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class SearchNewsUseCase @Inject constructor(
    private val repository: SearchNewsRepository,
    schedulerFacade: BaseSchedulerProvider,
    @Named("JsonParser") private val parser: JsonParser
) : BaseUseCase(repository,parser, schedulerFacade){

    fun getListEverythingNews(keyWord: String) : Observable<Any> {
        return repository.getListEverythingNews(keyWord)
            .observeOn(ui())
            .subscribeOn(io())
    }
}
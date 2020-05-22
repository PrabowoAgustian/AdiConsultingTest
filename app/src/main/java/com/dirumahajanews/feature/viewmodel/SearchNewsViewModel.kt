package com.dirumahajanews.feature.viewmodel

import com.dirumahajanews.base.viewmodel.BaseViewModel
import com.dirumahajanews.constant.LiveDataTag
import com.dirumahajanews.feature.domain.SearchNewsUseCase
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.dirumahajanews.utils.callback.DiRumahAjaNewsAdapter
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SearchNewsViewModel @Inject constructor(
    private val useCase: SearchNewsUseCase
) : BaseViewModel(useCase){

    var dataNews : List<NewsTopHeadline>? = null

    fun getListEverythingNews(keyWord: String){
        addDisposable(
            useCase.getListEverythingNews(keyWord)
                .doOnSubscribe {
                    response().value = Response.loading()
                }
                .subscribe(object : DiRumahAjaNewsAdapter<Any>() {
                    override fun accept(t: Any) {
                        super.accept(t)
                        val response = getBaseResponseNews(t)
                        if (response?.status == "ok" && response.articles != null){
                            val type = object : TypeToken<List<NewsTopHeadline>>() {}.type
                            dataNews = getDataList(response.articles, type)
                            response().value = Response.success(
                                dataNews,
                                LiveDataTag.getDataNewsSuccess
                            )
                        } else {
                            response().value = Response.error(
                                "", LiveDataTag.getDataNewsFailed
                            )
                        }
                    }
                }, handleError())
        )
    }
}
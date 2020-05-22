package com.dirumahajanews.feature.viewmodel

import com.google.gson.reflect.TypeToken
import com.dirumahajanews.base.viewmodel.BaseViewModel
import com.dirumahajanews.constant.LiveDataTag
import com.dirumahajanews.feature.domain.HomeUseCase
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.pojo.response.NewsCategoryList
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.dirumahajanews.utils.callback.DiRumahAjaNewsAdapter
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : BaseViewModel(useCase) {

    var dataListCategory : List<NewsCategoryList>? = null
    var dataTopHeadline : List<NewsTopHeadline>? = null

    var country : String = "id"

    fun getListCategory(){
        addDisposable(
            useCase.getListCategory()
                .doOnSubscribe {
                    response().value = Response.loading()
                }
                .subscribe(object : DiRumahAjaNewsAdapter<Any>() {
                    override fun accept(t: Any) {
                        super.accept(t)
                        val response = getBaseResponseSource(t)
                        if (response?.status == "ok" && response.source != null){
                            val type = object : TypeToken<List<NewsCategoryList>>() {}.type
                            dataListCategory = getDataList(response.source, type)
                            response().value = Response.success(
                                dataListCategory,
                                LiveDataTag.getListCategorySuccess
                            )
                        } else {
                            response().value = Response.error(
                                "", LiveDataTag.getListCategoryFailed
                            )
                        }
                    }
                }, handleError())
        )
    }

    fun getListTopHeadlineNews(){
        addDisposable(
            useCase.getListTopHeadlineNews(country)
                .doOnSubscribe {
                    response().value = Response.loading()
                }
                .subscribe(object : DiRumahAjaNewsAdapter<Any>() {
                    override fun accept(t: Any) {
                        super.accept(t)
                        val response = getBaseResponseNews(t)
                        if (response?.status == "ok" && response.articles != null){
                            val type = object : TypeToken<List<NewsTopHeadline>>() {}.type
                            dataTopHeadline = getDataList(response.articles, type)
                            response().value = Response.success(
                                dataTopHeadline,
                                LiveDataTag.getListTopHeadlineNewsSuccess
                            )
                        } else {
                            response().value = Response.error(
                                "", LiveDataTag.getListTopHeadlineNewsFailed
                            )
                        }
                    }
                }, handleError())
        )
    }
}
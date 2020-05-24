package com.dirumahajanews.base.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dirumahajanews.base.domain.BaseUseCase
import com.dirumahajanews.constant.HttpResponse
import com.dirumahajanews.pojo.common.ErrorContent
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.pojo.response.BaseCategoryResponse
import com.dirumahajanews.pojo.response.BaseNewsResponse
import com.dirumahajanews.utils.callback.DiRumahAjaNewsAdapter
import com.dirumahajanews.utils.callback.DiRumahAjaNewsThrowableAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.apache.commons.lang3.StringUtils
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.reflect.Type
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

open class BaseViewModel (private val baseUseCase: BaseUseCase) : ViewModel() {
    private val response: MutableLiveData<Response> = MutableLiveData()
    private val disposables = CompositeDisposable()
    var activity: FragmentActivity? = null

    fun onStart(permissions: List<String>) {
        if (activity != null) {
            for (permission in permissions) {
                addDisposable(
                    baseUseCase.getRequestPermission(activity!!, permission)
                        .subscribe(object : DiRumahAjaNewsAdapter<Boolean>() {
                            override fun accept(t: Boolean) {
                                super.accept(t)
                                if (t) {
                                    response().value = Response.success("", permission)
                                } else {
                                    response().value = Response.error("", permission)
                                }
                            }
                        })
                )
            }
        }
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun handleError(): DiRumahAjaNewsThrowableAdapter {
        return object : DiRumahAjaNewsThrowableAdapter() {
            override fun accept(throwable: Throwable) {
                super.accept(throwable)
                if (throwable is HttpException && throwable.response().code() == HttpResponse.unAuthorize) {
                } else {
                    handleError(throwable)
                }
            }
        }
    }

    fun handleError(throwable: Throwable) {
        val errorMessage: String?
        var errorType: String = HttpResponse.httpError
        if (StringUtils.isNotBlank(throwable.message)) {
            when (throwable) {
                is UnknownHostException -> {
                    errorType = HttpResponse.connectionError
                    errorMessage = "Unknown Host"
                }
                is SocketException -> {
                    errorMessage = "Socket TimeOut"
                }
                is SocketTimeoutException -> {
                    errorType = HttpResponse.connectionError
                    errorMessage = "Socket TimeOut"
                }
                is SSLHandshakeException -> {
                    errorMessage = "SSL Error"
                }
                is FileNotFoundException -> {
                    errorMessage = "File Not Found"
                }
                is IOException -> {
                    errorMessage = "Something when wrong"
                }
                is IllegalArgumentException -> {
                    errorMessage = "Something when wrong"
                }
                is IllegalStateException -> {
                    errorMessage = "Something when wrong"
                }
                is HttpException -> {
                    when (throwable.response().code()) {
                        HttpResponse.badRequest -> {
                            errorMessage = getErrorMassage(throwable, "Bad Request")
                        }
                        HttpResponse.unAuthorize -> {
                            errorMessage = getErrorMassage(throwable, "UnAuthorized")
                        }
                        HttpResponse.notFound -> {
                            errorMessage = getErrorMassage(throwable, "Error Not Found")
                        }
                        HttpResponse.methodNotAllowed -> {
                            errorMessage =
                                getErrorMassage(throwable, "Method Not Allowed")
                        }
                        HttpResponse.internalServerError -> {
                            errorMessage =
                                getErrorMassage(throwable, "Internal Server Error")
                        }
                        HttpResponse.badGateway -> {
                            errorMessage = getErrorMassage(throwable, "Bad Gateway")
                        }
                        HttpResponse.serviceUnavailable -> {
                            errorMessage =
                                getErrorMassage(throwable, "Service UnAvailable")
                        }
                        else -> {
                            errorMessage =
                                getErrorMassage(throwable, "Something When Wrong")
                        }
                    }
                }
                is Exception -> {
                    errorMessage = "Something when wrong"
                }
                else -> {
                    errorMessage = "Something when wrong"
                }
            }
        } else {
            errorMessage = throwable.fillInStackTrace().toString()
        }
        if (response().hasActiveObservers()) {
            response().value = Response.error(errorMessage ?: "Something when wrong", errorType)
        }
    }

    private fun getErrorMassage(
        throwable: HttpException,
        msg: String
    ): String? {
        return try {
            val errorContent: ErrorContent? = baseUseCase.getJsonParser()?.getObject(
                throwable.response().errorBody()!!.string(),
                ErrorContent::class.java
            )
            when {
                StringUtils.isNotBlank(
                    errorContent?.errorDescription
                ) -> errorContent?.errorDescription
                StringUtils.isNotBlank(
                    errorContent?.result
                ) -> errorContent?.result
                StringUtils.isNotBlank(
                    errorContent?.message
                ) -> errorContent?.message
                StringUtils.isNotBlank(
                    errorContent?.error
                ) -> errorContent?.error
                else -> "Request Failed"
            }
        } catch (e: Exception) {
            msg
        }
    }

    fun response(): MutableLiveData<Response> {
        return response
    }

    fun dispose() {
        disposables.dispose()
    }

    protected fun getBaseResponseSource(data: Any?) : BaseCategoryResponse? {
        return getRealData(data, BaseCategoryResponse::class.java)
    }

    protected fun getBaseResponseNews(data: Any?) : BaseNewsResponse? {
        return getRealData(data, BaseNewsResponse::class.java)
    }

    protected fun <T> getRealData(data: Any?, cls: Class<T>?): T? {
        return baseUseCase.getJsonParser()?.getObject(data, cls)
    }

    protected fun <T> getDataList(data: Any?, type: Type): T? {
        return baseUseCase.getJsonParser()?.getObjects(data, type)
    }

    open fun unBinding() {
        onCleared()
    }
}
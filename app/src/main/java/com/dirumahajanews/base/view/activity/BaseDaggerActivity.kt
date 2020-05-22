package com.dirumahajanews.base.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dirumahajanews.base.viewmodel.BaseViewModel
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.pojo.common.Status
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseDaggerActivity<T : BaseViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModelFactory = viewModel.createFactory()
        ViewModelProviders.of(this, viewModelFactory).get(viewModel.javaClass)
        viewModel.activity = this
        viewModel.response().observe(this, Observer { this.processResponse(it) })
    }

    protected fun processResponse(response: Response) {
        when (response.status) {
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESS -> {
                dismissLoading()
                doOnResponseSuccess(response)
            }
            Status.ERROR -> {
                dismissLoading()
            }
        }

    }

    private fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
        val viewModel = this
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
        }
    }

    abstract fun doOnResponseSuccess(response: Response)

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
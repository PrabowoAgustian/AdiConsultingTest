package com.dirumahajanews.feature.view.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import com.dirumahajanews.R
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.dirumahajanews.base.view.activity.BaseDaggerActivity
import com.dirumahajanews.base.viewmodel.BaseViewModel
import dagger.android.AndroidInjection

@Suppress("DEPRECATION")
abstract class MainActivity <T : BaseViewModel> : BaseDaggerActivity<T>() {

    @BindView(R.id.bottomNavigation)
    lateinit var bottomNavigationViewEx: BottomNavigationViewEx

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
        configureBottomNavigation()
    }

    private fun initViewModel() {
        val viewModelFactory = viewModel.createFactory()
        ViewModelProviders.of(this, viewModelFactory).get(viewModel.javaClass)
        viewModel.response().observe(this, Observer { this.processResponse(it) })
    }

    private fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
        val viewModel = this
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
        }
    }

    private var isHome = false

    protected open fun configureBottomNavigation() {
        bottomNavigationViewEx.selectedItemId = getItemSelected()
        bottomNavigationViewEx.itemIconTintList = null
        bottomNavigationViewEx.isItemHorizontalTranslationEnabled = false
        bottomNavigationViewEx.enableAnimation(false)
        val menu = bottomNavigationViewEx.menu
        val items = menu.findItem(getItemSelected())
        items.icon = resources.getDrawable(getIconSelected())
        bottomNavigationViewEx.setOnNavigationItemSelectedListener { item: MenuItem ->
            if (bottomNavigationViewEx.selectedItemId != item.itemId) {
                when (item.itemId) {
                    R.id.new_navigation_home -> {
                    }
                    R.id.new_navigation_layanan -> {
                    }
                    R.id.new_navigation_kategori -> {
                    }
                    R.id.new_navigation_video ->{}
                }
            }
            false
        }
    }

    fun setHome(isHome: Boolean) {
        this.isHome = isHome
    }

    override fun onBackPressed() {
    }

    abstract fun getIconSelected(): Int

    abstract fun getItemSelected(): Int
}

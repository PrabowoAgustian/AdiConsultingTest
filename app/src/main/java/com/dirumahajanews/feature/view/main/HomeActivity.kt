package com.dirumahajanews.feature.view.main

import android.content.Intent
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.OnClick
import com.dirumahajanews.constant.Constanta
import com.dirumahajanews.constant.LiveDataTag
import com.dirumahajanews.feature.view.detailpage.DetailNewsActivity
import com.dirumahajanews.feature.view.searchpage.SearchNewsActivity
import com.dirumahajanews.R
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.feature.viewmodel.HomeViewModel
import com.dirumahajanews.helper.StringHelper
import com.dirumahajanews.helper.TimeHelper
import com.dirumahajanews.helper.ViewHelper
import com.dirumahajanews.pojo.adapter.ListCategoryAdapter
import com.dirumahajanews.pojo.adapter.ListNewsAdapter
import com.dirumahajanews.pojo.response.NewsCategoryList
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.dirumahajanews.utils.image.GlideUtils
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.content_header_home.*
import kotlinx.android.synthetic.main.content_section_news.*

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class HomeActivity : MainActivity<HomeViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private val categorNewsAdapter = FastItemAdapter<ListCategoryAdapter>()
    private val topHeadlineAdapter = FastItemAdapter<ListNewsAdapter>()

    override fun layoutRes(): Int {
        return R.layout.activity_homepage
    }

    override fun getIconSelected(): Int {
        return R.drawable.ic_home
    }

    override fun getItemSelected(): Int {
        return R.id.new_navigation_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initComponent()
    }

    private fun initComponent() {
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.soft_blue))
        configureItemAdapter(newsRecycleView, topHeadlineAdapter)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initData() {
        viewModel.getListCategory()
        viewModel.getListTopHeadlineNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unBinding()
    }

    override fun onRefresh() {
        swipeRefresh.isRefreshing = false
        initData()
    }

    override fun doOnResponseSuccess(response: Response) {
        when (response.type) {
            LiveDataTag.getListCategorySuccess -> {
                showListCategory(response.source as List<NewsCategoryList>)
            }
            LiveDataTag.getListTopHeadlineNewsSuccess -> {
                showListTopHeadline(response.articles as List<NewsTopHeadline>)
                ViewHelper.showView(areaHeader)
                ViewHelper.showView(topHeadlineTitle)
            }
        }
    }

    private fun showListTopHeadline(list: List<NewsTopHeadline>) {
        topHeadlineAdapter.clear()
        for (data in list){
            topHeadlineAdapter.add(ListNewsAdapter(data))
        }

        topHeadlineAdapter.withOnClickListener { _, _, item, _ ->
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(Constanta.dataTopHeadline, item.newsTopHeadline)
            showActivity(intent)
            true
        }

        list[0].urlToImage.let {
            it?.let { it1 ->
                GlideUtils.setFotoWithUrl(this,
                    it1, imageHeaderNews)
            }
        }
        titleNewsTextview.text = list[0].title
        subTitleNewsTextview.text = StringHelper.getStringBuilderToString(list[0].author," | ",
            TimeHelper.getDateFormatedNew(list[0].publishedAt).toString()
        )

        imageHeaderNews.setOnClickListener {
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(Constanta.dataTopHeadline, list[0])
            showActivity(intent)
        }
    }

    private fun showListCategory(list: List<NewsCategoryList>) {
        categorNewsAdapter.clear()
        for (data in list){
            categorNewsAdapter.add(ListCategoryAdapter(data))
        }
    }

    @OnClick(R.id.searchNews)
    fun searchNewClicked(){
        showActivityAndFinishAllActivity(this, SearchNewsActivity::class.java)
    }
}
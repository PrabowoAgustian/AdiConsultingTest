package com.dirumahajanews.feature.view.searchpage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindString
import butterknife.OnClick
import com.dirumahajanews.R
import com.dirumahajanews.base.view.activity.BaseDaggerActivity
import com.dirumahajanews.constant.Constanta
import com.dirumahajanews.constant.LiveDataTag
import com.dirumahajanews.feature.view.detailpage.DetailNewsActivity
import com.dirumahajanews.feature.view.main.HomeActivity
import com.dirumahajanews.feature.viewmodel.SearchNewsViewModel
import com.dirumahajanews.helper.StringHelper
import com.dirumahajanews.pojo.adapter.ListNewsAdapter
import com.dirumahajanews.pojo.common.Response
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_search_news.*
import kotlinx.android.synthetic.main.activity_search_news.swipeRefresh
import kotlinx.android.synthetic.main.content_section_news.newsRecycleView
import kotlinx.android.synthetic.main.toolbar_search.*

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class SearchNewsActivity : BaseDaggerActivity<SearchNewsViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private val newsAdapter = FastItemAdapter<ListNewsAdapter>()

    var dataNews : List<NewsTopHeadline>? = null

    @BindString(R.string.label_result_search)
    lateinit var labelResultSearch : String


    override fun layoutRes(): Int {
        return R.layout.activity_search_news
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.soft_blue))
        configureItemAdapter(newsRecycleView, newsAdapter)
        searchNews.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadData()
            }

        })
    }

    private fun loadData() {
        if (searchNews.text.toString().length >= 5){
            viewModel.getListEverythingNews(searchNews.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unBinding()
    }

    override fun onRefresh() {
        swipeRefresh.isRefreshing = false
        loadData()
    }

    override fun doOnResponseSuccess(response: Response) {
        when(response.type){
            LiveDataTag.getDataNewsSuccess ->{
                dataNews = response.articles as List<NewsTopHeadline>
                showDataNews(dataNews!!)
            }
        }
    }

    private fun showDataNews(list: List<NewsTopHeadline>) {
        newsAdapter.clear()
        for (data in list){
            newsAdapter.add(ListNewsAdapter(data))
        }

        newsAdapter.withOnClickListener { _, _, item, _ ->
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(Constanta.dataTopHeadline, item.newsTopHeadline)
            showActivity(intent)
            true
        }

        resultSearchTextView.text = StringHelper.getStringBuilderToString(list.size.toString(), " ",
            labelResultSearch, " -", searchNews.text.toString())
    }

    @OnClick(R.id.backButton)
    fun backButtonClicked(){
        showActivity(this, HomeActivity::class.java)
    }
}
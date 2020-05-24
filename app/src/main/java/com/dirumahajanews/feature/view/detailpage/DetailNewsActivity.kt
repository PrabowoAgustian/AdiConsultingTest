package com.dirumahajanews.feature.view.detailpage

import android.os.Bundle
import butterknife.BindString
import butterknife.OnClick
import com.dirumahajanews.R
import com.dirumahajanews.base.view.activity.BaseActivity
import com.dirumahajanews.constant.Constanta
import com.dirumahajanews.helper.StringHelper
import com.dirumahajanews.helper.TimeHelper
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.dirumahajanews.utils.image.GlideUtils
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.content_detail_news.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailNewsActivity : BaseActivity() {

    @BindString(R.string.label_quote)
    lateinit var labelQuote : String

    override fun layoutRes(): Int {
        return R.layout.activity_detail_news
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {
        val newsTopHeadline : NewsTopHeadline = intent.getParcelableExtra(Constanta.dataTopHeadline)
        sectionNewsTextView.text = StringHelper.getStringBuilderToString(labelQuote, " | ", newsTopHeadline.source.name)
        newsTopHeadline.urlToImage?.let { GlideUtils.setFotoWithUrl(this, it, imageHeaderNews) }
        titleNewsTextview.text = newsTopHeadline.title
        byLineNewsTextView.text = newsTopHeadline.author
        dateNewsTextView.text = TimeHelper.getDateFormatedNew(newsTopHeadline.publishedAt)
        contentNewsTextView.text = newsTopHeadline.description
        urlNewsTextView.text = newsTopHeadline.url
    }

    @OnClick(R.id.backButton)
    fun backButtonClicked(){
        onBackPressed()
    }
}
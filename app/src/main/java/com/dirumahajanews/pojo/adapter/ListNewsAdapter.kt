package com.dirumahajanews.pojo.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dirumahajanews.R
import com.dirumahajanews.helper.StringHelper
import com.dirumahajanews.helper.TimeHelper
import com.dirumahajanews.pojo.response.NewsTopHeadline
import com.dirumahajanews.utils.image.GlideUtils
import com.mikepenz.fastadapter.items.AbstractItem

class ListNewsAdapter (var newsTopHeadline: NewsTopHeadline) : AbstractItem<ListNewsAdapter, ListNewsAdapter.ViewHolder>() {

    override fun getType(): Int {
        return R.id.news_id
    }

    override fun getLayoutRes(): Int {
        return R.layout.adapter_list_news
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open val newsImage: AppCompatImageView = itemView.findViewById(R.id.newImageView)
        val titleNews: AppCompatTextView = itemView.findViewById(R.id.titleNewsTextview)
        val authorNews : AppCompatTextView = itemView.findViewById(R.id.authorTextview)
        val context: Context = itemView.context
    }

    override fun bindView(
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        super.bindView(holder, payloads)
        holder.titleNews.text = newsTopHeadline.title
        GlideUtils.setFotoRoundedWithUrl(holder.context, newsTopHeadline.urlToImage, holder.newsImage)
        holder.authorNews.text = TimeHelper.getDateFormatedNew(newsTopHeadline.publishedAt)?.let {
            StringHelper.getStringBuilderToString(newsTopHeadline.author," | ",
                it
            )
        }
    }

    override fun unbindView(holder: ViewHolder) {
    }
}
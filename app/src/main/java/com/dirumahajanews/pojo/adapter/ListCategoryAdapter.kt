package com.dirumahajanews.pojo.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dirumahajanews.R
import com.dirumahajanews.helper.StringHelper
import com.dirumahajanews.pojo.response.NewsCategoryList
import com.mikepenz.fastadapter.items.AbstractItem

class ListCategoryAdapter (var listNewsCategroy : NewsCategoryList) : AbstractItem<ListCategoryAdapter, ListCategoryAdapter.ViewHolder>() {

    override fun getType(): Int {
        return R.id.section_id
    }

    override fun getLayoutRes(): Int {
        return R.layout.adapter_list_section_news
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTextView : AppCompatTextView = itemView.findViewById(R.id.sectionTextView)
    }

    override fun bindView(
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        super.bindView(holder, payloads)
        holder.sectionTextView.text = StringHelper.getStringBuilderToString("#", listNewsCategroy.category)
    }

    override fun unbindView(holder: ViewHolder) {
        holder.sectionTextView.text = null
    }
}
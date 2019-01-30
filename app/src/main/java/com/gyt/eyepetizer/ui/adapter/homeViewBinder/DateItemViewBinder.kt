package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_home_date.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/1/24 5:31 PM
 * @describer TODO
 */
class DateItemViewBinder : ItemViewBinder<HomeBean.Issue.Item, DateItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_home_date, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: HomeBean.Issue.Item) {
        holder.itemView.title.text = item.data.text
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
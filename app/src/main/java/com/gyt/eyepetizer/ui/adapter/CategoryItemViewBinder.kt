package com.gyt.eyepetizer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_category.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/1/24 5:31 PM
 * @describer TODO
 */
class CategoryItemViewBinder: ItemViewBinder<Category, CategoryItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Category) {
        holder.itemView.title.text = item.title
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
}
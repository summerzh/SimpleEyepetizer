package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.GallerySnapHelper
import com.gyt.eyepetizer.utils.SpaceItemDecoration
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_horizontal_scroll_card.view.*
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/2/19 4:41 PM
 * @describer TODO
 */
class HorizontalScrollCardViewBinder : ItemViewBinder<HomeBean.Item, HorizontalScrollCardViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_horizontal_scroll_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        val adapter = MultiTypeAdapter()
        adapter.register(HomeBean.Item.Data.DataItem::class.java, BannerItemViewBinder())
        viewHolder.itemView.mRecyclerView?.run {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
//            LinearSnapHelper().attachToRecyclerView(this)
//            RecyclerViewDivider.with(this.context)
//                    .size(30)
//                    .color(this.context.resources.getColor(R.color.white))
//                    .inset(30, 30)
//                    .asSpace()
//                    .build()
//                    .addTo(this)

            SpaceItemDecoration.with(this.context)
                    .space(7)
                    .edge(30)
                    .layoutManager(SpaceItemDecoration.LINEARLAYOUT)
                    .build()
                    .addTo(this)
            GallerySnapHelper().attachToRecyclerView(this)
        }
        adapter.items = item.data.itemList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

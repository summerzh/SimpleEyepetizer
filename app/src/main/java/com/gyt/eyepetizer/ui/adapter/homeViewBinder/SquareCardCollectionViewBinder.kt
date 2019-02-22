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
import kotlinx.android.synthetic.main.item_square_card_collection.view.*
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/2/19 4:37 PM
 * @describer TODO
 */
class SquareCardCollectionViewBinder : ItemViewBinder<HomeBean.Item, SquareCardCollectionViewBinder.ViewHolder>() {
    private var mRecyclerView: RecyclerView? = null

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_square_card_collection, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        val adapter = MultiTypeAdapter()
        adapter.register(HomeBean.Item.Data.DataItem::class.java, BannerItemViewBinder())
        adapter.items = item.data.itemList

        viewHolder.itemView?.apply {
            mTvHeaderTitle.text = item.data.header.title
            if (mRecyclerView == null) {
                mRecyclerView = viewHolder.itemView.recyclerView?.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                    itemAnimator = DefaultItemAnimator()
                    SpaceItemDecoration.with(this.context)
                            .space(this.context.resources.getDimensionPixelSize(R.dimen.common_divider_space_2))
                            .edge(this.context.resources.getDimensionPixelSize(R.dimen.common_edge_space_15))
                            .layoutManager(SpaceItemDecoration.LINEARLAYOUT)
                            .build()
                            .addTo(this)
                    GallerySnapHelper().attachToRecyclerView(this)
                }
            }
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}


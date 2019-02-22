package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.DisplayUtils
import com.gyt.eyepetizer.utils.loadRoundCornerPic
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_home_banner.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/1/29 2:12 PM
 * @describer TODO
 */
class BannerItemViewBinder : ItemViewBinder<HomeBean.Item.Data.DataItem, BannerItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_home_banner, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, banItem: HomeBean.Item.Data.DataItem) {
        viewHolder.itemView?.apply {
            val edge = this.context.resources.getDimension(R.dimen.common_edge_space_15)
            val space = this.context.resources.getDimension(R.dimen.common_divider_space_2)
            val width = DisplayUtils.getDisplayWidth(context) - (space + edge) * 2
            val height = width * 0.6
            mImageView.loadRoundCornerPic(context, banItem.data.image, width.toInt(), height.toInt())
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.DisplayUtils
import com.gyt.eyepetizer.utils.loadLargePic
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
            mImageView.loadLargePic(context, banItem.data.image)
            mImageView.layoutParams.width = DisplayUtils.getDisplayWidth(context) - 75
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
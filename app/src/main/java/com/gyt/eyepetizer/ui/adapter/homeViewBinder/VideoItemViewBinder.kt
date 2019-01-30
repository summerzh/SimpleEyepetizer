package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.simplereader.R
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/1/29 2:17 PM
 * @describer TODO
 */
class VideoItemViewBinder : ItemViewBinder<HomeBean.Issue.Item, VideoItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_home_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Issue.Item) {
        viewHolder.itemView?.run {
//            mIvCover.loadUrl(context, item.data.cover.feed)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
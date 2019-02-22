package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.loadRoundCornerPic
import com.gyt.eyepetizer.utils.loadSmallCirclePic
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_home_video.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/2/19 4:36 PM
 * @describer TODO
 */
class FollowCardViewBinder : ItemViewBinder<HomeBean.Item, FollowCardViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_home_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        viewHolder.itemView.run {
            item?.run {
                mIvCover.loadRoundCornerPic(context, data.content.data.cover.feed)
                mIvHeadPortrait.loadSmallCirclePic(context, data.header.icon)
                mTvTitle.text = data.content.data.title
                mTvDesc.text = data.header.title
                mTvDate.visibility = if (!TextUtils.isEmpty(data.text)) View.VISIBLE else View.GONE
                mTvDate.text = if (!TextUtils.isEmpty(data.text)) data.text else ""
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
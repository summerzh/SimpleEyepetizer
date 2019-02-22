package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.getDisplayWidth
import com.gyt.eyepetizer.utils.loadRoundCornerPic
import com.gyt.eyepetizer.utils.secondToTime
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_video_small_card.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/2/19 4:36 PM
 * @describer TODO
 */
class VideoSmallCardViewBinder : ItemViewBinder<HomeBean.Item, VideoSmallCardViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_video_small_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        val displayWidth = getDisplayWidth(viewHolder.itemView.context)

        viewHolder.itemView.run {
            val context = this.context
            item.data?.run {
                val width = (displayWidth * 0.5).toInt()
                val height = (width * 0.6).toInt()
                // displayWidth * 0.5.toInt()得到的是0，注意不要写成这种形式
                mIvCover.loadRoundCornerPic(context, cover.feed, width, height)
                mTvPlayTime.text = secondToTime(duration)
                mTvTitle.text = title
                mTvContent.text = "#$category / ${author.name}"
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
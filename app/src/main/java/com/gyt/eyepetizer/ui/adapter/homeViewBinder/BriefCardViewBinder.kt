package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.loadRoundCornerPic
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_brief_card.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/2/19 4:34 PM
 * @describer TODO
 */
class BriefCardViewBinder : ItemViewBinder<HomeBean.Item, BriefCardViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_brief_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        viewHolder.itemView?.apply {
            mIvIcon.loadRoundCornerPic(this.context, item.data.icon)
            mTvTitle.text = item.data.title
            mTvContent.text = item.data.description
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

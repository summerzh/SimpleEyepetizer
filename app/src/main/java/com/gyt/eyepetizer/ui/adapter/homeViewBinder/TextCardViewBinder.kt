package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_text_card.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/2/19 4:33 PM
 * @describer TODO
 */
class TextCardViewBinder : ItemViewBinder<HomeBean.Item, TextCardViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_text_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: HomeBean.Item) {
        viewHolder.itemView?.apply {
            item.data?.apply {
                when (type) {
                    "header5" -> {
                        mTvHeaderTitle.text = text
                        mRlFooter.visibility = View.GONE
                        mLlHeader.visibility = View.VISIBLE

                        mIVHeaderArrow.visibility = if (TextUtils.isEmpty(actionUrl)) View.GONE else View.GONE
                    }
                    "footer2" -> {
                        mTvFooterTitle.text = text
                        mRlFooter.visibility = View.VISIBLE
                        mLlHeader.visibility = View.GONE
                    }
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

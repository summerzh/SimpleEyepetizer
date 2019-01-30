package com.gyt.eyepetizer.ui.adapter.homeViewBinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.utils.loadUrl
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.item_home_banner.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author gyt
 * @date on 2019/1/29 2:12 PM
 * @describer TODO
 */
class BannerItemViewBinder : ItemViewBinder<HomeBean, BannerItemViewBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_home_banner, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, homeBean: HomeBean) {

        val itemList = homeBean.issueList[0].itemList.toCollection(ArrayList())
        val picList: ArrayList<String> = ArrayList()
        val titleList: ArrayList<String> = ArrayList()
        itemList.forEach {
            picList.add(it.data.cover.feed)
            titleList.add(it.data.title)
        }

        with(viewHolder.itemView) {
            mBGABanner.run {
                setData(picList, titleList)
                setAutoPlayAble(itemList.size > 1)
                setAdapter { banner, _, imgUrl, position ->
                    banner.getItemImageView(position).loadUrl(context, imgUrl as String, R.drawable.ic_banner_placeholder)
                }
            }
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
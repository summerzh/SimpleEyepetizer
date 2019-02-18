package com.gyt.eyepetizer.ui.fragment.homefragment

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseFragment
import com.gyt.eyepetizer.utils.CustomFragmentViewPager
import com.gyt.eyepetizer.utils.ViewPagerHelper
import kotlinx.android.synthetic.main.fragment_home.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


/**
 * @author gyt
 * @date on 2019/2/15 5:04 PM
 * @describer TODO
 */
class HomeFragment : BaseFragment() {
    private val mTitleDataList by lazy { arrayListOf("发现", "推荐", "日报") }

    companion object {
        fun getInstance(): Fragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId() = com.gyt.simplereader.R.layout.fragment_home

    override fun intView() {

        val commonNavigator = CommonNavigator(context)
        fragmentManager?.let {
            mViewPager.adapter = CustomFragmentViewPager(it, 3)
        }

        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return mTitleDataList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.GRAY
                colorTransitionPagerTitleView.selectedColor = Color.BLACK
                colorTransitionPagerTitleView.text = mTitleDataList[index]
                colorTransitionPagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }
        }
        mMagicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(mMagicIndicator, mViewPager)
    }

    override fun retryRequest() {
    }

}
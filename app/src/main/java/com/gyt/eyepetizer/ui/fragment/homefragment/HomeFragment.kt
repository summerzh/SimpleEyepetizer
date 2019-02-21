package com.gyt.eyepetizer.ui.fragment.homefragment

import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseFragment
import com.gyt.eyepetizer.utils.CustomFragmentViewPager
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @author gyt
 * @date on 2019/2/15 5:04 PM
 * @describer TODO
 */
class HomeFragment : BaseFragment() {
    private val mTitleDataArray by lazy { arrayOf("发现", "推荐", "日报") }

    companion object {
        fun getInstance(): Fragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId() = com.gyt.simplereader.R.layout.fragment_home

    override fun intView() {
        fragmentManager?.let {
            mViewPager.adapter = CustomFragmentViewPager(it, 3)
        }
        mSlidingTabLayout.setViewPager(mViewPager, mTitleDataArray)
        mSlidingTabLayout.currentTab = 2
    }

    override fun retryRequest() {
    }

}
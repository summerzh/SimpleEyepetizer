package com.gyt.eyepetizer.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gyt.eyepetizer.ui.fragment.homefragment.DailyFragment
import com.gyt.eyepetizer.ui.fragment.homefragment.DiscoveryFragment

/**
 * @author gyt
 * @date on 2019/2/15 5:50 PM
 * @describer TODO
 */
class CustomFragmentViewPager(fragmentManager: FragmentManager, private val count: Int) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            2 -> DailyFragment.getInstance()
            else -> DiscoveryFragment.getInstance()
        }
    }

    override fun getCount(): Int {
        return count
    }

}
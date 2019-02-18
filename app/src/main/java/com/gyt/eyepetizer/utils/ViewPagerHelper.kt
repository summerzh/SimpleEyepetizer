package com.gyt.eyepetizer.utils

import androidx.viewpager.widget.ViewPager
import net.lucode.hackware.magicindicator.MagicIndicator


/**
 * @author gyt
 * @date on 2019/2/15 5:17 PM
 * @describer TODO
 */
object ViewPagerHelper {
    fun bind(magicIndicator: MagicIndicator, viewPager: ViewPager) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                magicIndicator.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                magicIndicator.onPageScrollStateChanged(state)
            }
        })
    }
}
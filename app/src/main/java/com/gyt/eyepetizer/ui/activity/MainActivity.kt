package com.gyt.eyepetizer.ui.activity

import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseActivity
import com.gyt.eyepetizer.ui.fragment.DailySelectionFragment
import com.gyt.eyepetizer.ui.fragment.DiscoveryFragment
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun retryRequest() {
    }

    private var mCurrentFragment: Fragment = Fragment()
    private var mBookDiscoveryFragment: Fragment? = null
    private var mBookShelfFragment: Fragment? = null
    private var mCurrentPosition = 0
    private var mFragmentList = ArrayList<Fragment>()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        bottom_navigation.setIconVisibility(true)
        bottom_navigation.enableShiftingMode(false)
        bottom_navigation.enableItemShiftingMode(false)

        switchFragment(mCurrentPosition)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_book_discovery -> {
                    switchFragment(0)
                    true
                }
                R.id.menu_book_shelf -> {
                    switchFragment(1)
                    true
                }
                else -> false
            }
        }
    }

    private fun switchFragment(position: Int) {
        when (position) {
            0 -> {
                showHideFragment(mBookDiscoveryFragment
                        ?: DailySelectionFragment.getInstance(), "bookdiscoveryfragment")
            }
            1 -> {
                showHideFragment(mBookShelfFragment
                        ?: DiscoveryFragment.getInstance(), "bookshelffragment")
            }
        }
        mCurrentPosition = position
    }

    private fun showHideFragment(fragment: Fragment, tag: String) {
        if (mCurrentFragment != fragment) {
            val mTransaction = supportFragmentManager.beginTransaction()
            mTransaction.hide(mCurrentFragment)
            mCurrentFragment = fragment
            if (!fragment.isAdded()) {
                mTransaction.add(R.id.fl_container, fragment, tag)
            }
            mTransaction.show(fragment).commitAllowingStateLoss()
        }
    }
}

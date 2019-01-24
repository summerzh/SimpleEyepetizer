package com.gyt.simplereader.ui.activity

import android.support.v4.app.Fragment
import com.gyt.simplereader.R
import com.gyt.simplereader.base.BaseActivity
import com.gyt.simplereader.ui.fragment.BookDiscoveryFragment
import com.gyt.simplereader.ui.fragment.BookShelfFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
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
    }

    override fun initEvent() {
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
                        ?: BookDiscoveryFragment.getInstance(), "bookdiscoveryfragment")
            }
            1 -> {
                showHideFragment(mBookShelfFragment
                        ?: BookShelfFragment.getInstance(), "bookshelffragment")
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

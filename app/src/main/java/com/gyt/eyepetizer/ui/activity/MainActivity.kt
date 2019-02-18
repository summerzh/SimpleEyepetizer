package com.gyt.eyepetizer.ui.activity

import android.Manifest
import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseActivity
import com.gyt.eyepetizer.ui.fragment.homefragment.DiscoveryFragment
import com.gyt.eyepetizer.ui.fragment.homefragment.HomeFragment
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : BaseActivity() {
    override fun retryRequest() {
    }

    private var mCurrentFragment: Fragment = Fragment()
    private var mHomeFragment: Fragment? = null
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

        checkPermission()
    }

    private fun switchFragment(position: Int) {
        when (position) {
            0 -> {
                showHideFragment(mHomeFragment
                        ?: HomeFragment.getInstance(), "homefragment")
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

    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }
}

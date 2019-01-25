package com.gyt.eyepetizer.ui.fragment

import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseFragment
import com.gyt.simplereader.R

/**
 * @author gyt
 * @date on 2019/1/21 2:52 PM
 * @describer TODO
 */
class BookShelfFragment: BaseFragment(){

    companion object {
        fun getInstance(): Fragment {
            val bookShelfFragment = BookShelfFragment()
            return bookShelfFragment
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_bookshelf

    override fun intView() {
    }

    override fun initEvent() {
    }

}
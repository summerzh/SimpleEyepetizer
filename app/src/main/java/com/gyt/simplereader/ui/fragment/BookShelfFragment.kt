package com.gyt.simplereader.ui.fragment

import android.support.v4.app.Fragment
import com.gyt.simplereader.R
import com.gyt.simplereader.base.BaseFragment

/**
 * @author gyt
 * @date on 2019/1/21 2:52 PM
 * @describer TODO
 */
class BookShelfFragment: BaseFragment(){

    companion object {
        fun getInstance(): Fragment{
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
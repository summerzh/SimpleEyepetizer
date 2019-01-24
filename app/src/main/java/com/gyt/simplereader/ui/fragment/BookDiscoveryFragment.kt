package com.gyt.simplereader.ui.fragment

import android.support.v4.app.Fragment
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.HttpManager
import com.gyt.kotlindemo.http.RxUtils
import com.gyt.simplereader.BuildConfig
import com.gyt.simplereader.R
import com.gyt.simplereader.base.BaseFragment
import com.gyt.simplereader.utils.loadUrl
import com.gyt.simplereader.utils.toast
import kotlinx.android.synthetic.main.fragment_bookdiscovery.*

/**
 * @author gyt
 * @date on 2019/1/21 2:51 PM
 * @describer TODO
 */
class BookDiscoveryFragment: BaseFragment(){

    companion object {
        fun getInstance(): Fragment{
            val bookDiscoveryFragment = BookDiscoveryFragment()
            return bookDiscoveryFragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_bookdiscovery

    override fun intView() {
        addSubscription(HttpManager.httpService.getSearchBookPackage("我")
                .compose(RxUtils.ioMainSchedule())
                .subscribe({
                    iv_cover.loadUrl(context!!, BuildConfig.BASE_IMG_URL + it.books[0].cover)
                    tv_title.text = it.books[0].title
                }, {toast(ExceptionHandle.handleException(it))}))
    }

    override fun initEvent() {
    }

}
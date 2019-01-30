package com.gyt.eyepetizer.ui.fragment

import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.base.BaseMvpFragment
import com.gyt.eyepetizer.mvp.contract.DiscoveryContract
import com.gyt.eyepetizer.mvp.presenter.DiscoveryPresenter
import com.gyt.eyepetizer.utils.getAutoDispose
import com.gyt.simplereader.R
import com.uber.autodispose.AutoDisposeConverter

/**
 * @author gyt
 * @date on 2019/1/21 2:52 PM
 * @describer TODO
 */
class DiscoveryFragment : BaseMvpFragment<DiscoveryPresenter, DiscoveryContract.View>() {

    companion object {
        fun getInstance(): Fragment {
            val bookShelfFragment = DiscoveryFragment()
            return bookShelfFragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_bookshelf

    override fun intView() {
    }

    override fun retryRequest() {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return getAutoDispose(this)
    }

}
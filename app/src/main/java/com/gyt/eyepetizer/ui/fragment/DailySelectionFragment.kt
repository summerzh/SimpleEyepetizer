package com.gyt.eyepetizer.ui.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.gyt.eyepetizer.base.BaseFragment
import com.gyt.eyepetizer.mvp.contract.DailySelectionContract
import com.gyt.eyepetizer.mvp.presenter.DailySelectionPresenter
import com.gyt.eyepetizer.ui.adapter.CategoryItemViewBinder
import com.gyt.simplereader.R
import kotlinx.android.synthetic.main.fragment_bookdiscovery.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/1/21 2:51 PM
 * @describer TODO
 */
class DailySelectionFragment: BaseFragment(), DailySelectionContract.View{

    private val mAdapter by lazy { MultiTypeAdapter() }
    private val mPresenter by lazy{ DailySelectionPresenter()}

    companion object {

        fun getInstance(): Fragment {
            val bookDiscoveryFragment = DailySelectionFragment()
            return bookDiscoveryFragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_bookdiscovery

    override fun intView() {
        mPresenter.attachView(this)
        mAdapter.register(Category::class.java, CategoryItemViewBinder())

        recyclerView.layoutManager = GridLayoutManager(context, SPANCOUNT)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        mAdapter.items =

    }

    override fun showData() {
        mPresenter.requestData()
    }

    override fun loadMoreData() {
    }

    override fun showError() {
    }

    override fun showLoading() {
        mMultiStateView?.showLoading()
    }

    override fun showContent() {
        mMultiStateView?.showContent()
    }

    override fun retryRequest() {
    }

}
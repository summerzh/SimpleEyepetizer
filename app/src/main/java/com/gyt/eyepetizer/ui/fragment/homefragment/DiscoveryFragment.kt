package com.gyt.eyepetizer.ui.fragment.homefragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.gyt.eyepetizer.base.BaseMvpFragment
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.mvp.contract.DiscoveryContract
import com.gyt.eyepetizer.mvp.presenter.DiscoveryPresenter
import com.gyt.eyepetizer.ui.adapter.homeViewBinder.FollowCardViewBinder
import com.gyt.eyepetizer.ui.adapter.homeViewBinder.HorizontalScrollCardViewBinder
import com.gyt.eyepetizer.ui.adapter.homeViewBinder.TextCardViewBinder
import com.gyt.eyepetizer.utils.getAutoDispose
import com.gyt.simplereader.R
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_discovery.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/1/21 2:52 PM
 * @describer 首页-发现
 */
class DiscoveryFragment : BaseMvpFragment<DiscoveryPresenter, DiscoveryContract.View>(), DiscoveryContract.View {
    private val mAdapter by lazy { MultiTypeAdapter() }
    private val mList by lazy { ArrayList<Any>() }

    companion object {
        fun getInstance(): Fragment {
            val discoveryFragment = DiscoveryFragment()
            return discoveryFragment
        }
    }

    init {
        mPresenter = DiscoveryPresenter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_discovery

    override fun intView() {
        mPresenter?.apply {
            attachView(this@DiscoveryFragment)
            requestData()
        }

        mAdapter.register(HomeBean.Item::class.java).to(
                TextCardViewBinder(),
                FollowCardViewBinder(),
                HorizontalScrollCardViewBinder()
        ).withClassLinker { _, data ->
            when (data.type) {
                "textCard" -> TextCardViewBinder::class.java
                "followCard" -> FollowCardViewBinder::class.java
                "horizontalScrollCard" -> HorizontalScrollCardViewBinder::class.java
                else -> TextCardViewBinder::class.java
            }
        }

        mRecyclerView?.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }


        context?.let {
            RecyclerViewDivider.with(it)
                    .size(1)
                    .inset(it.resources.getDimensionPixelSize(R.dimen.common_space_15), it.resources.getDimensionPixelSize(R.dimen.common_space_15))
                    .hideLastDivider()
                    .build()
                    .addTo(mRecyclerView)
        }
        mAdapter.items = mList
    }

    override fun setData(itemList: ArrayList<HomeBean.Item>) {
        mList.clear()
        mList.addAll(itemList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun retryRequest() {

    }

    override fun showLoading() {
        mMultiStateView.showLoading()
    }

    override fun hideLoading() {
        mMultiStateView.showContent()
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return getAutoDispose(this)
    }

}
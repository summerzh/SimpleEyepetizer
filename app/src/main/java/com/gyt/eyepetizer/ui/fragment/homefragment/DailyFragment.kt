package com.gyt.eyepetizer.ui.fragment.homefragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.gyt.eyepetizer.base.BaseMvpFragment
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.mvp.contract.DailyContract
import com.gyt.eyepetizer.mvp.presenter.DailyPresenter
import com.gyt.eyepetizer.ui.adapter.homeViewBinder.FollowCardViewBinder
import com.gyt.eyepetizer.utils.getAutoDispose
import com.gyt.simplereader.R
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_daily.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/1/21 2:51 PM
 * @describer TODO
 */
class DailyFragment : BaseMvpFragment<DailyPresenter, DailyContract.View>(), DailyContract.View {
    private val mAdapter by lazy { MultiTypeAdapter() }
    private val mList by lazy { ArrayList<Any>() }
    private var mRefresh = false
    private var mLoadMore = false

    init {
        mPresenter = DailyPresenter()
    }

    companion object {

        fun getInstance(): Fragment {
            val dailySelectionFragment = DailyFragment()
            return dailySelectionFragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_daily

    override fun intView() {
        mRefresh = false
        mPresenter?.attachView(this)
        mPresenter?.requestData()

        mAdapter.register(HomeBean.Item::class.java, FollowCardViewBinder())

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

        // 下来刷新
        mSmartRefreshLayout?.setOnRefreshListener {
            mRefresh = true
            mPresenter?.requestData()
            it.finishRefresh()
        }

        // 加载更多
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = mRecyclerView.childCount
                    val itemCount = mRecyclerView.layoutManager?.itemCount
                    val firstVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {// 屏幕中第一个的index和屏幕中的item的count == item总数
                        if (!mLoadMore) {
                            mLoadMore = true
                            mPresenter?.loadMoreData()
                        }
                    }
                }
            }
        })
    }

    override fun setHomeData(itemList: ArrayList<HomeBean.Item>) {
        mList.clear()
        mList.addAll(itemList)
        mAdapter.notifyDataSetChanged()
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Item>) {
        mLoadMore = false
        mList.addAll(itemList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun showLoading() {
        if (!mRefresh) {
            mRefresh = false
            mMultiStateView?.showLoading()
        }
    }

    override fun hideLoading() {
        mMultiStateView?.showContent()
    }

    override fun retryRequest() {
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return getAutoDispose(this)
    }
}
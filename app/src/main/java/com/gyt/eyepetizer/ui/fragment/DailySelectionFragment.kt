package com.gyt.eyepetizer.ui.fragment

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.gyt.eyepetizer.base.BaseMvpFragment
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.mvp.contract.DailySelectionContract
import com.gyt.eyepetizer.mvp.presenter.DailySelectionPresenter
import com.gyt.eyepetizer.ui.adapter.homeViewBinder.VideoItemViewBinder
import com.gyt.eyepetizer.utils.getAutoDispose
import com.gyt.simplereader.R
import com.uber.autodispose.AutoDisposeConverter
import kotlinx.android.synthetic.main.fragment_bookdiscovery.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author gyt
 * @date on 2019/1/21 2:51 PM
 * @describer TODO
 */
class DailySelectionFragment : BaseMvpFragment<DailySelectionPresenter, DailySelectionContract.View>(), DailySelectionContract.View {
    private val mAdapter by lazy { MultiTypeAdapter() }
    private val mList by lazy { ArrayList<Any>() }

    init {
        mPresenter = DailySelectionPresenter()
    }

    companion object {

        fun getInstance(): Fragment {
            val dailySelectionFragment = DailySelectionFragment()
            return dailySelectionFragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_bookdiscovery

    override fun intView() {
        mPresenter?.attachView(this)
        mPresenter?.requestData()

        mAdapter.register(HomeBean.Item::class.java, VideoItemViewBinder())

        recyclerView?.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            RecyclerViewDivider.with(context!!)
                    .size(1)
                    .inset(context.resources.getDimensionPixelSize(R.dimen.common_space_15), context.resources.getDimensionPixelSize(R.dimen.common_space_15))
                    .hideLastDivider()
                    .build()
                    .addTo(this)
        }

        mAdapter.items = mList
    }

    override fun setBannerData(homeBean: HomeBean) {
        mList.add(homeBean)
        mAdapter.notifyDataSetChanged()
    }

    override fun setHomeData(itemList: ArrayList<HomeBean.Item>) {
        mList.addAll(itemList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun showLoading() {
        mMultiStateView?.showLoading()
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
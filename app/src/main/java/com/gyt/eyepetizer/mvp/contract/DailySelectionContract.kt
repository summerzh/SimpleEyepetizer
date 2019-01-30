package com.gyt.eyepetizer.mvp.contract

import com.gyt.eyepetizer.base.IBaseView
import com.gyt.eyepetizer.base.IPresenter
import com.gyt.eyepetizer.beans.HomeBean

/**
 * @author gyt
 * @date on 2019/1/25 5:49 PM
 * @describer TODO
 */
interface DailySelectionContract {
    interface View : IBaseView {
        fun setBannerData(homeBean: HomeBean)

        fun setHomeData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {
        fun requestData()

        fun loadMoreData()
    }
}
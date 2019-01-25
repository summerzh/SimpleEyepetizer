package com.gyt.eyepetizer.mvp.contract

import com.gyt.eyepetizer.base.IBaseView
import com.gyt.eyepetizer.base.IPresenter

/**
 * @author gyt
 * @date on 2019/1/25 5:49 PM
 * @describer TODO
 */
interface DailySelectionContract{
    interface View : IBaseView{
        fun showData()

        fun loadMoreData()

        fun showError()
    }

    interface Presenter : IPresenter<View>{
        fun requestData()

        fun loadMoreData()
    }
}
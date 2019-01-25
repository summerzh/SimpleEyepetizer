package com.gyt.eyepetizer.mvp.presenter

import com.gyt.eyepetizer.base.BasePresenter
import com.gyt.eyepetizer.mvp.contract.DailySelectionContract

/**
 * @author gyt
 * @date on 2019/1/25 8:11 PM
 * @describer TODO
 */

class DailySelectionPresenter : BasePresenter<DailySelectionContract.View>(), DailySelectionContract.Presenter{


    override fun requestData() {

    }

    override fun loadMoreData() {
    }

}
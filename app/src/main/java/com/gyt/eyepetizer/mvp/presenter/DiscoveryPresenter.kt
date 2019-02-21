package com.gyt.eyepetizer.mvp.presenter

import com.gyt.eyepetizer.base.BasePresenter
import com.gyt.eyepetizer.mvp.contract.DiscoveryContract
import com.gyt.eyepetizer.mvp.model.DiscoveryModel
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.RxUtils

/**
 * @author gyt
 * @date on 2019/1/28 4:06 PM
 * @describer TODO
 */

class DiscoveryPresenter : BasePresenter<DiscoveryContract.View>(), DiscoveryContract.Presenter {
    private val mDiscoveryModel by lazy { DiscoveryModel() }

    override fun requestData() {
        checkViewAttach()
        mRootView?.apply {
            showLoading()
            mDiscoveryModel.getData()
                    .compose(RxUtils.ioMainSchedule())
                    .`as`(bindAutoDispose())
                    .subscribe({ homeBean ->
                        hideLoading()
                        setData(homeBean.itemList)
                    }, { throwable ->
                        mRootView?.apply {
                            hideLoading()
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    })
        }

    }

}

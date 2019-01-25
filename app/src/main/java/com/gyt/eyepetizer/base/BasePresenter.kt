package com.gyt.eyepetizer.base

/**
 * @author gyt
 * @date on 2019/1/25 6:00 PM
 * @describer TODO
 */
open class BasePresenter<V : IBaseView> : IPresenter<V> {

    var mRootView: IBaseView? = null
        private set

    override fun attachView(mRootView: V) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        this.mRootView = null
    }
}
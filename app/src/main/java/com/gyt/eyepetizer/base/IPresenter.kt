package com.gyt.eyepetizer.base

/**
 * @author gyt
 * @date on 2019/1/25 5:58 PM
 * @describer TODO
 */
interface IPresenter<in V: IBaseView>{

    fun attachView(mRootView: V)

    fun detachView()
}
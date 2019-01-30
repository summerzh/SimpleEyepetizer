package com.gyt.eyepetizer.base

/**
 * @author gyt
 * @date on 2019/1/28 3:16 PM
 * @describer TODO
 */
abstract class BaseMvpActivity<in P : IPresenter<V>, V : IBaseView> : BaseActivity(), IBaseView {

    private val mPresenter: P? = null

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachView()
    }
}
package com.gyt.eyepetizer.base

/**
 * @author gyt
 * @date on 2019/1/28 3:43 PM
 * @describer TODO
 */
abstract class BaseMvpFragment<P : BasePresenter<V>, V : IBaseView> : BaseFragment(), IBaseView {

    internal var mPresenter: P? = null

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
    }


}
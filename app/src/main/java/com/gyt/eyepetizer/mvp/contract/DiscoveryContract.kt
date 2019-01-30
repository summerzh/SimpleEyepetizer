package com.gyt.eyepetizer.mvp.contract

import com.gyt.eyepetizer.base.IBaseView
import com.gyt.eyepetizer.base.IPresenter

/**
 * @author gyt
 * @date on 2019/1/28 4:07 PM
 * @describer TODO
 */
interface DiscoveryContract{
    interface View : IBaseView{

    }
    interface Presenter : IPresenter<View>{

    }
}
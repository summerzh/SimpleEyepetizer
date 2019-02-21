package com.gyt.eyepetizer.mvp.contract

import com.gyt.eyepetizer.base.IBaseView
import com.gyt.eyepetizer.base.IPresenter
import com.gyt.eyepetizer.beans.HomeBean

/**
 * @author gyt
 * @date on 2019/1/28 4:07 PM
 * @describer TODO
 */
interface DiscoveryContract{
    interface View : IBaseView{

        fun setData(itemList: ArrayList<HomeBean.Item>)

        fun showError(msg: String, errorCode: Int)
    }
    interface Presenter : IPresenter<View>{
        fun requestData()
    }
}
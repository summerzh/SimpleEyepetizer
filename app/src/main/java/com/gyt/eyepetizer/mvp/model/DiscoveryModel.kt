package com.gyt.eyepetizer.mvp.model

import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.kotlindemo.http.HttpManager
import io.reactivex.Flowable

/**
 * @author gyt
 * @date on 2019/2/19 11:46 AM
 * @describer TODO
 */

class DiscoveryModel {

    fun getData(): Flowable<HomeBean> {
        return HttpManager.httpService.discovery()
    }

}
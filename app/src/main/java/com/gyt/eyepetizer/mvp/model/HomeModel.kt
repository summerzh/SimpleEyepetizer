package com.gyt.eyepetizer.mvp.model

import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.kotlindemo.http.HttpManager
import io.reactivex.Flowable

/**
 * @author gyt
 * @date on 2019/1/29 11:19 AM
 * @describer TODO
 */
class HomeModel {

    fun getHomeData(num: Int): Flowable<HomeBean> {
        return HttpManager.httpService.getHomeData(num)
    }

    fun loadMoreData(nextUrl: String): Flowable<HomeBean> {
        return HttpManager.httpService.getMoreHomeData(nextUrl)
    }
}
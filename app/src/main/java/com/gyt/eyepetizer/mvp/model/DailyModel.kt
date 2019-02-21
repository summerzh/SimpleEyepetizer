package com.gyt.eyepetizer.mvp.model

import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.kotlindemo.http.HttpManager
import io.reactivex.Flowable

/**
 * @author gyt
 * @date on 2019/1/29 11:19 AM
 * @describer TODO
 */
class DailyModel {

    fun getDailyData(): Flowable<HomeBean> {
        return HttpManager.httpService.getDailyData()
    }

    fun loadMoreData(nextUrl: String): Flowable<HomeBean> {
        return HttpManager.httpService.getMoreDailyData(nextUrl)
    }
}
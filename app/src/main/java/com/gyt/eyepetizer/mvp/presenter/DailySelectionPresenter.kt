package com.gyt.eyepetizer.mvp.presenter

import com.gyt.eyepetizer.base.BasePresenter
import com.gyt.eyepetizer.beans.HomeBean
import com.gyt.eyepetizer.mvp.contract.DailySelectionContract
import com.gyt.eyepetizer.mvp.model.HomeModel
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.RxUtils

/**
 * @author gyt
 * @date on 2019/1/25 8:11 PM
 * @describer TODO
 */

class DailySelectionPresenter : BasePresenter<DailySelectionContract.View>(), DailySelectionContract.Presenter {

    private var mNewHomeBean: HomeBean? = null
    private val mHomeModel: HomeModel by lazy { HomeModel() }
    private var mNextPageUrl: String? = null

    override fun requestData() {
        checkViewAttach()

        mRootView?.showLoading()
        mHomeModel.getHomeData(20)
                .flatMap { homeBean ->
                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val bannerItemList = homeBean.issueList[0].itemList

                    bannerItemList.filter { item ->
                        item.type == "banner2" || item.type == "horizontalScrollCard"
                    }.forEach { item ->
                        //移除 item
                        bannerItemList.remove(item)
                    }

                    bannerItemList.forEach { item->
                        item.type = "banner"
                    }
                    mNewHomeBean = homeBean
                    mRootView?.apply {
                        setBannerData(homeBean)
                    }
                    mHomeModel.loadMoreData(homeBean.nextPageUrl)
                }
                .compose(RxUtils.ioMainSchedule())
                .`as`(mRootView!!.bindAutoDispose())
                .subscribe({ homeBean ->
                    mRootView?.apply {
                        hideLoading()
                        val newItemList = homeBean.issueList[0].itemList
                        mNextPageUrl = homeBean.nextPageUrl

                        newItemList.filter { item ->
                            item.type == "banner2" || item.type == "horizontalScrollCard"
                        }.forEach { item ->
                            newItemList.remove(item)
                        }

                        setHomeData(newItemList)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        hideLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
    }

    override fun loadMoreData() {
        mNextPageUrl?.let {
            mHomeModel.loadMoreData(it)
                    .compose(RxUtils.ioMainSchedule())
                    .`as`(mRootView!!.bindAutoDispose())
                    .subscribe({ homeBean ->
                        mRootView?.apply {
                            val newItemList = homeBean.issueList[0].itemList
                            mNextPageUrl = homeBean.nextPageUrl

                            newItemList.filter { item ->
                                item.type == "banner2" || item.type == "horizontalScrollCard"
                            }.forEach { item ->
                                newItemList.remove(item)
                            }

                            setHomeData(newItemList)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    })
        }
    }

}
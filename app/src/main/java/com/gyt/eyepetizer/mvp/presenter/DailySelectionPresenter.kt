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
        mHomeModel.getHomeData()
                .compose(RxUtils.ioMainSchedule())
                .`as`(mRootView!!.bindAutoDispose())
                .subscribe({ homeBean ->
                    mRootView?.apply {
                        hideLoading()
                        val newItemList = homeBean.itemList
                        mNextPageUrl = homeBean.nextPageUrl

                        // 移除掉今日社区精选的item
                        newItemList.filter { item ->
                            item.type == "textCard" && item.data.text == "今日社区精选" || item.type == "pictureFollowCard" || item.type == "autoPlayFollowCard"
                        }.forEach {
                            newItemList.remove(it)
                        }

                        // 将type为textCard的text显示在下一个item中
                        val textCardList = newItemList.filter { item ->
                            item.type == "textCard"
                        }

                        textCardList.forEach { newItemList[newItemList.indexOf(it) + 1].data.text = it.data.text }

                        newItemList.iterator()?.run {
                            while (hasNext()) {
                                if (textCardList.contains(next())) {
                                    remove()
                                }
                            }
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
                            val newItemList = homeBean.itemList
                            mNextPageUrl = homeBean.nextPageUrl

                            newItemList.filter { item ->
                                item.type == "textCard" && item.data.text == "今日社区精选" || item.type == "pictureFollowCard" || item.type == "autoPlayFollowCard"
                            }.forEach {
                                newItemList.remove(it)
                            }

                            val textCardList = newItemList.filter { item ->
                                item.type == "textCard"
                            }

                            textCardList.forEach { item -> newItemList[newItemList.indexOf(item) + 1].data.text = item.data.text }
                            newItemList.iterator()?.run {
                                while (hasNext()) {
                                    if (textCardList.contains(next())) {
                                        remove()
                                    }
                                }
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
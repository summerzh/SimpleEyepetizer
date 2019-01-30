package com.gyt.kotlindemo.http

import com.gyt.eyepetizer.beans.HomeBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @author gyt
 * @date on 2018/11/27 11:51 AM
 * @describer TODO
 */
interface HttpApi {


    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getHomeData(@Query("num") num: Int): Flowable<HomeBean>

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Flowable<HomeBean>
//
//    /**
//     * 根据item id获取相关视频
//     */
//    @GET("v4/video/related?")
//    fun getRelatedData(@Query("id") id: Long): Observable<HomeBean.Issue>
//
//    /**
//     * 获取分类
//     */
//    @GET("v4/categories")
//    fun getCategory(): Observable<ArrayList<CategoryBean>>
//
//    /**
//     * 获取分类详情List
//     */
//    @GET("v4/categories/videoList?")
//    fun getCategoryDetailList(@Query("id") id: Long): Observable<HomeBean.Issue>
//
//    /**
//     * 获取更多的 Issue
//     */
//    @GET
//    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>
//
//    /**
//     * 获取全部排行榜的Info（包括，title 和 Url）
//     */
//    @GET("v4/rankList")
//    fun getRankList(): Observable<TabInfoBean>
//
//    /**
//     * 获取搜索信息
//     */
//    @GET("v1/search?&num=10&start=10")
//    fun getSearchData(@Query("query") query :String) : Observable<HomeBean.Issue>
//
//    /**
//     * 热门搜索词
//     */
//    @GET("v3/queries/hot")
//    fun getHotWord(): Observable<ArrayList<String>>
//
//    /**
//     * 关注
//     */
//    @GET("v4/tabs/follow")
//    fun getFollowInfo(): Observable<HomeBean.Issue>
//
//    /**
//     * 作者信息
//     */
//    @GET("v4/pgcs/detail/tab?")
//    fun getAuthorInfo(@Query("id") id: Long): Observable<AuthorInfoBean>
}
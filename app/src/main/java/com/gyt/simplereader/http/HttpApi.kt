package com.gyt.kotlindemo.http

import com.gyt.simplereader.base.SearchBookBean
import com.gyt.simplereader.beans.BookDetailBean
import com.gyt.simplereader.beans.packages.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author gyt
 * @date on 2018/11/27 11:51 AM
 * @describer TODO
 */
interface HttpApi {

    /**
     * 推荐书籍
     * @param gender
     * @return
     */
    @GET("/book/recommend")
    fun getRecommendBookPackage(@Query("gender") gender: String): Flowable<RecommendBookPackage>


    /**
     * 获取书籍的章节总列表
     * @param bookId
     * @param view 默认参数为:chapters
     * @return
     */
    @GET("/mix-atoc/{bookId}")
    fun getBookChapterPackage(@Path("bookId") bookId: String, @Query("view") view: String): Flowable<BookChapterPackage>

    /**
     * 章节的内容
     * 这里采用的是同步请求。
     * @param url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    fun getChapterInfoPackage(@Path("url") url: String): Flowable<ChapterInfoPackage>

    /*******************************Community *******************************************************/
    /**
     * 获取综合讨论区、原创区，女生区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble:综合讨论区
     * original：原创区
     * girl:女生区
     * @param duration   all
     * @param sort       updated(默认排序)
     * created(最新发布)
     * comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET("/post/by-block")
    fun getBookCommentList(@Query("block") block: String, @Query("duration") duration: String, @Query("sort") sort: String, @Query("type") type: String, @Query("start") start: String, @Query("limit") limit: String, @Query("distillate") distillate: String): Flowable<BookCommentPackage>


    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     * created(最新发布)
     * comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/help")
    fun getBookHelpList(@Query("duration") duration: String, @Query("sort") sort: String, @Query("start") start: String, @Query("limit") limit: String, @Query("distillate") distillate: String): Flowable<BookHelpsPackage>

    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)
     * created(最新发布)
     * helpful(最有用的)
     * comment-count(最多评论)
     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)...
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/review")
    fun getBookReviewList(@Query("duration") duration: String, @Query("sort") sort: String, @Query("type") type: String, @Query("start") start: String, @Query("limit") limit: String, @Query("distillate") distillate: String): Flowable<BookReviewPackage>


    /***********************************帖子详情*******************************************************/
    /**
     * 获取综合讨论区帖子详情 :/post/{detailId}
     * @param detailId ->_id
     * @return
     */
    @GET("/post/{detailId}")
    fun getCommentDetailPackage(@Path("detailId") detailId: String): Flowable<CommentDetailPackage>


    /**
     * 获取书评区帖子详情
     *
     * @param detailId->_id
     * @return
     */
    @GET("/post/review/{detailId}")
    fun getReviewDetailPacakge(@Path("detailId") detailId: String): Flowable<ReviewDetailPackage>


    /**
     * 获取书荒区帖子详情
     *
     * @param detailId->_id
     * @return
     */
    @GET("/post/help/{detailId}")
    fun getHelpsDetailPackage(@Path("detailId") detailId: String): Flowable<HelpsDetailPackage>


    /**
     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
     *
     * @param detailId -> _id
     * @return
     */
    @GET("/post/{detailId}/comment/best")
    fun getBestCommentPackage(@Path("detailId") detailId: String): Flowable<CommentsPackage>

    /**
     * 获取综合讨论区帖子详情内的评论列表        :/post/{disscussionId}/comment
     * 获取书评区、书荒区帖子详情内的评论列表     :/post/review/{disscussionId}/comment
     * @param detailId->_id
     * @param start              0
     * @param limit              30
     * @return
     */
    @GET("/post/{detailId}/comment")
    fun getCommentPackage(@Path("detailId") detailId: String, @Query("start") start: String, @Query("limit") limit: String): Flowable<CommentsPackage>

    @GET("/post/review/{detailId}/comment")
    fun getBookCommentPackage(@Path("detailId") detailId: String, @Query("start") start: String, @Query("limit") limit: String): Flowable<CommentsPackage>


    /************************************find****************************************************/

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    fun getBillboardPackage(): Flowable<BillboardPackage>

    /**
     * 获取单一排行榜
     * 周榜：rankingId-> _id
     * 月榜：rankingId-> monthRank
     * 总榜：rankingId-> totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    fun getBillBookPackage(@Path("rankingId") rankingId: String): Flowable<BillBookPackage>


    /*******************************分类***************************************/
    /**
     * 获取分类
     *
     * @return
     */
    @GET("/cats/lv2/statistics")
    fun getBookSortPackage(): Flowable<BookSortPackage>

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    fun getBookSubSortPackage(): Flowable<BookSubSortPackage>

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    fun getSortBookPackage(@Query("gender") gender: String, @Query("type") type: String, @Query("major") major: String, @Query("minor") minor: String, @Query("start") start: Int, @Query("limit") limit: Int): Flowable<SortBookPackage>

    /********************************主题书单**************************************8*/

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * 如:http://api.zhuishushenqi.com/book-list?duration=last-seven-days&sort=collectorCount&start=0&limit=20&tag=%E9%83%BD%E5%B8%82&gender=male
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    @GET("/book-list")
    fun getBookListPackage(@Query("duration") duration: String, @Query("sort") sort: String,
                           @Query("start") start: String, @Query("limit") limit: String,
                           @Query("tag") tag: String, @Query("gender") gender: String): Flowable<BookListPackage>

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    @GET("/book-list/tagType")
    fun getBookTagPackage(): Flowable<BookTagPackage>

    /**
     * 获取书单详情
     *
     * @return
     */
    @GET("/book-list/{bookListId}")
    fun getBookListDetailPackage(@Path("bookListId") bookListId: String): Flowable<BookListDetailPackage>


    /*************************书籍详情**********************************/

    /**
     * 书籍热门评论
     *
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    fun getHotCommnentPackage(@Query("book") book: String): Flowable<HotCommentPackage>

    /**
     * 书籍推荐书单
     * @param bookId
     * @param limit
     * @return
     */
    @GET("/book-list/{bookId}/recommend")
    fun getRecommendBookListPackage(@Path("bookId") bookId: String, @Query("limit") limit: String): Flowable<RecommendBookListPackage>

    /**
     * 书籍详情
     * @param bookId
     * @return
     */
    @GET("/book/{bookId}")
    fun getBookDetail(@Path("bookId") bookId: String): Flowable<BookDetailBean>

    /**
     * 根据书籍的 Tag 进行检索
     * @param tags
     * @param start
     * @param limit
     * @return
     */
    @GET("/book/by-tags")
    fun getTagSearchPackage(@Query("tags") tags: String, @Query("start") start: String, @Query("limit") limit: String): Flowable<TagSearchPackage>


    /************************************搜索书籍 */
    @GET("/book/hot-word")
    fun getHotWordPackage(): Flowable<HotWordPackage>

    /**
     * 关键字自动补全
     *
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    fun getKeyWordPacakge(@Query("query") query: String): Flowable<KeyWordPackage>

    /**
     * 书籍查询
     *
     * @param query:作者名或者书名
     * @return
     */
    @GET("/book/fuzzy-search")
    fun getSearchBookPackage(@Query("query") query: String, @Query("start") start: Int = 0, @Query("limit") limit: Int = 20): Flowable<SearchBookBean>
}
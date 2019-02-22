package com.gyt.kotlindemo.http

import com.gyt.eyepetizer.http.LoggingInterceptor
import com.gyt.simplereader.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author gyt
 * @date on 2018/11/27 2:04 PM
 * @describer TODO
 */
object HttpManager {
    val httpService: HttpApi by lazy {
        getRetrofit().create(HttpApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor()) //日志,所有的请求响应度看到
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build()
    }
}
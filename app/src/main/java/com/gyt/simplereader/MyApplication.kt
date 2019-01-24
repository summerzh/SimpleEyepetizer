package com.gyt.simplereader

import android.app.Application
import android.content.Context
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.properties.Delegates

/**
 * @author gyt
 * @date on 2019/1/22 5:26 PM
 * @describer TODO
 */

class MyApplication: Application(){

    companion object {
        var mContext:Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        val strategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .logStrategy(LogCatStrategy())
                .methodCount(2)
                .tag("logger")
                .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(strategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    /**
     * 解决android studio3.1 log错乱的问题
     */
    inner class LogCatStrategy : LogStrategy {
        private var last: Int = 0
        override fun log(priority: Int, tag: String?, message: String) {
            Log.println(priority, randomKey() + tag!!, message)
        }
        private fun randomKey(): String {
            var random = (10 * Math.random()).toInt()
            if (random == last) {
                random = (random + 1) % 10
            }
            last = random
            return random.toString()
        }
    }
}
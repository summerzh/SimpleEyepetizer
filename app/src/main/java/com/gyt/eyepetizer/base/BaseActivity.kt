package com.gyt.eyepetizer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyt.eyepetizer.widget.MultiStateView

/**
 * @author gyt
 * @date on 2019/1/18 4:40 PM
 * @describer TODO
 */

abstract class BaseActivity : AppCompatActivity() {

    private var mMultiStateView: MultiStateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initEvent()
        setOnRetryClickListener()
    }

    private fun setOnRetryClickListener() {
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_ERROR)?.setOnClickListener { retryRequest() }
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_NO_NETWORK)?.setOnClickListener { retryRequest() }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initEvent()

    abstract fun retryRequest()

}
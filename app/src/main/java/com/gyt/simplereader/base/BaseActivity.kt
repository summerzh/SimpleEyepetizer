package com.gyt.simplereader.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author gyt
 * @date on 2019/1/18 4:40 PM
 * @describer TODO
 */

abstract class BaseActivity : AppCompatActivity(){

    private var mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!mCompositeDisposable.isDisposed)
            mCompositeDisposable.clear()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initEvent()

    fun addSubscription(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

}
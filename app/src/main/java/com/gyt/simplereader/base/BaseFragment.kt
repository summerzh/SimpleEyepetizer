package com.gyt.simplereader.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author gyt
 * @date on 2019/1/21 2:45 PM
 * @describer TODO
 */

@SuppressLint("ValidFragment")
abstract class BaseFragment : Fragment(){
    private var mCompositeDisposable = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intView()
        initEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(!mCompositeDisposable.isDisposed)
            mCompositeDisposable.clear()
    }

    abstract fun getLayoutId(): Int

    abstract fun intView()

    abstract fun initEvent()

    fun addSubscription(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

}
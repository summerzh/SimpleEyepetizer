package com.gyt.eyepetizer.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyt.eyepetizer.widget.MultiStateView

/**
 * @author gyt
 * @date on 2019/1/21 2:45 PM
 * @describer TODO
 */

@SuppressLint("ValidFragment")
abstract class BaseFragment : Fragment(){
    private var mMultiStateView: MultiStateView? = null
    private var mIsVisibleToUser = false
    private var mIsFirstLoad = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intView()
        setOnRetryClickListener()
    }

    private fun setOnRetryClickListener() {
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_ERROR)?.setOnClickListener { retryRequest() }
        mMultiStateView?.getView(MultiStateView.VIEW_STATE_NO_NETWORK)?.setOnClickListener { retryRequest() }
    }

    /**
     * 配合viewPager使用，调用setUserVisibleHint
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(lazyLoadMode()){
            if(isVisibleToUser){
                mIsVisibleToUser = true
                onVisible()
            }else{
                mIsVisibleToUser = false
            }
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(lazyLoadMode()){
            if(!hidden){
                mIsVisibleToUser = true
                onVisible()
            }else{
                mIsVisibleToUser = false
            }
        }
    }

    private fun onVisible() {
        if(mIsVisibleToUser && mIsFirstLoad){
            mIsFirstLoad = false
            lazyLoadViewAndEvent()
        }
    }

    private fun lazyLoadViewAndEvent() {

    }

    /**
     * kotlin中protected不能修饰class
     */
    open fun lazyLoadMode(): Boolean{
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun getLayoutId(): Int

    abstract fun intView()

    abstract fun retryRequest()

}
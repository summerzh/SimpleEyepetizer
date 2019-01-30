package com.gyt.eyepetizer.base

import com.uber.autodispose.AutoDisposeConverter

/**
 * @author gyt
 * @date on 2019/1/25 5:57 PM
 * @describer TODO
 */
interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun <T> bindAutoDispose(): AutoDisposeConverter<T>
}
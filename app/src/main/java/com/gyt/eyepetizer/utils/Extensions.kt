package com.gyt.eyepetizer.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.gyt.eyepetizer.MyApplication
import com.gyt.eyepetizer.utils.glide.GlideApp
import com.gyt.simplereader.R
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @author gyt
 * @date on 2018/11/28 9:50 AM
 * @describer 扩展函数
 */

fun ImageView.loadLargePic(context: Context, url: String, @DrawableRes placeHolder: Int = R.drawable.ic_banner_placeholder) {

    GlideApp.with(context)
            .load(url)
            .placeholder(placeHolder)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(this)
}


fun ImageView.loadSmallCirclePic(context: Context, url: String) {
    GlideApp.with(context)
            .load(url)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.mContext, message, duration).show()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.mContext, message, duration).show()
}

fun View.dip2px(dipValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun View.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun <T> Activity.getAutoDispose(context: AppCompatActivity): AutoDisposeConverter<T> {
    return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(context, Lifecycle.Event.ON_DESTROY))
}

fun <T> Fragment.getAutoDispose(context: Fragment): AutoDisposeConverter<T> {
    return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(context, Lifecycle.Event.ON_DESTROY))
}
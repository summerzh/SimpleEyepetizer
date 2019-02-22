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
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

fun ImageView.loadRoundCornerPic(context: Context, url: String, @DrawableRes placeHolder: Int = R.drawable.ic_banner_placeholder) {
    GlideApp.with(context)
            .load(url)
            .error(placeHolder)
            .placeholder(placeHolder)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
}


fun ImageView.loadSmallCirclePic(context: Context, url: String) {
    GlideApp.with(context)
            .load(url)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
}

fun ImageView.loadRoundCornerPic(context: Context, url: String, width: Int, height: Int, @DrawableRes placeHolder: Int = R.drawable.ic_banner_placeholder) {
    GlideApp.with(context)
            .load(url)
            .placeholder(placeHolder)
            .error(placeHolder)
            .transition(DrawableTransitionOptions().crossFade())
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .override(width, height)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
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

fun getDisplayWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getDisplayHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

fun secondToTime(time: Int): String {
    var timeStr: String? = null
    var hour = 0
    var minute = 0
    var second = 0
    if (time <= 0)
        return "00:00"
    else {
        minute = time / 60
        if (minute < 60) {
            second = time % 60
            timeStr = unitFormat(minute) + ":" + unitFormat(second)
        } else {
            hour = minute / 60
            if (hour > 99)
                return "99:59:59"
            minute %= 60
            second = time - hour * 3600 - minute * 60
            timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second)
        }
    }
    return timeStr
}

private fun unitFormat(i: Int): String {
    var retStr: String? = null
    retStr = if (i in 0..9)
        "0" + Integer.toString(i)
    else
        "" + i
    return retStr
}
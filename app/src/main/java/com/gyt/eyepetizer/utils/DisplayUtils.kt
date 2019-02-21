package com.gyt.eyepetizer.utils

import android.content.Context

/**
 * @author gyt
 * @date on 2019/2/20 3:55 PM
 * @describer TODO
 */
class DisplayUtils {

    companion object {
        fun getDisplayWidth(context: Context): Int {
            return context.resources.displayMetrics.widthPixels
        }

        fun getDisplayHeight(context: Context): Int {
            return context.resources.displayMetrics.heightPixels
        }
    }

}
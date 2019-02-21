package com.gyt.eyepetizer.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.IntDef
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author gyt
 * @date on 2019/2/20 5:54 PM
 * @describer TODO
 */
class SpaceItemDecoration private constructor(private val space: Int,
                                              private val edge: Int,
                                              private val layoutManager: Int) : RecyclerView.ItemDecoration() {

    companion object {
        const val LINEARLAYOUT = 0
        const val GRIDLAYOUT = 1

        fun with(context: Context): Builder = Builder(context)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        when (layoutManager) {
            LINEARLAYOUT ->
                setLinearLayoutSpaceItemDecoration(outRect, view, parent, state)
            GRIDLAYOUT -> {
                val gridLayoutManager = parent.layoutManager as GridLayoutManager
                //列数
                val spanCount = gridLayoutManager.getSpanCount()
                setGridLayoutSpaceItemDecoration(outRect, view, parent, state)
            }
            else -> setLinearLayoutSpaceItemDecoration(outRect, view, parent, state)
        }

    }


    private fun setLinearLayoutSpaceItemDecoration(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.adapter?.itemCount ?: 0
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        when {
            parent.getChildLayoutPosition(view) == 0 -> {
                outRect.left = edge
                outRect.right = space
            }
            parent.getChildLayoutPosition(view) == childCount - 1 -> {
                outRect.right = edge
                outRect.left = space
            }
            else -> {
                outRect.left = space
                outRect.right = space
            }
        }
    }


    private fun setGridLayoutSpaceItemDecoration(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter
    }

    fun addTo(recyclerView: RecyclerView) {
        removeFrom(recyclerView)
        recyclerView.addItemDecoration(this)
    }

    private fun removeFrom(recyclerView: RecyclerView) {
        recyclerView.removeItemDecoration(this)
    }

    class Builder(private val context: Context) {
        private var space: Int = 0
        private var edge: Int = 0
        private var layoutManager = LINEARLAYOUT

        fun space(space: Int) = apply { this.space = space }

        fun edge(edge: Int) = apply { this.edge = edge }

        fun layoutManager(@LayoutManager layoutManager: Int) = apply { this.layoutManager = layoutManager }

        fun build() = SpaceItemDecoration(space, edge, layoutManager)
    }

    @IntDef(LINEARLAYOUT, GRIDLAYOUT)
    @Retention(RetentionPolicy.SOURCE)
    annotation class LayoutManager(val type: Int = LINEARLAYOUT)
}



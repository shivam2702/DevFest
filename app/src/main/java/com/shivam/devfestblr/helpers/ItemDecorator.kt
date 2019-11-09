package com.shivam.devfestblr.helpers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator(
    private var top: Int = 0,
    var bottom: Int = 0,
    var left: Int = 0,
    var right: Int = 0
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = bottom
        outRect.top = top
        outRect.right = right
        outRect.left = left
    }
}
package br.com.app5m.mulheremfoco.helper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

open class DividerItemDecorationLastExcluded (private val dividerDrawable: Drawable) :
    RecyclerView.ItemDecoration() {

    private val dividerWidth = dividerDrawable.intrinsicWidth
    private val dividerHeight = dividerDrawable.intrinsicHeight

    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            rect.right = // Add space/"padding" on right side
                if (childAdapterPosition == adapter.itemCount - 1) 0    // No "padding"
                else dividerWidth                                       // Drawable width "padding"
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            parent.children // Displayed children on screen
                .forEach { view ->
                    val childAdapterPosition = parent.getChildAdapterPosition(view)
                        .let { if (it == RecyclerView.NO_POSITION) return else it }
                    if (childAdapterPosition != adapter.itemCount - 1) {
                        val left = view.right
                        val top = parent.paddingTop
                        val right = left + dividerWidth
                        val bottom = top + dividerHeight - parent.paddingBottom
                        dividerDrawable.bounds = Rect(left, top, right, bottom)
                        dividerDrawable.draw(canvas)
                    }
                }
        }
    }
}
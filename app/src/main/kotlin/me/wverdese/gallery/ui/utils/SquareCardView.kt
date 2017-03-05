package me.wverdese.gallery.ui.utils

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View

/**
 * Sets the height of the card same as the provided width.
 */
class SquareCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : CardView(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        val size = View.MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }
}
package com.oleksandrkarpiuk.recipemaster.utils

import android.text.TextPaint
import android.text.style.SuperscriptSpan

class AlignSuperScript (shiftPercentage: Float) : SuperscriptSpan() {
    private var fontScale = 3
    private var shiftPercentage = 0f

    init {
        if (shiftPercentage > 0.0 && shiftPercentage < 1.0) this.shiftPercentage = shiftPercentage
    }

    override fun updateDrawState(tp: TextPaint) {
        val ascent = tp.ascent()
        tp.textSize = tp.textSize / fontScale
        val newAscent = tp.fontMetrics.ascent
        tp.baselineShift += ((ascent - ascent * shiftPercentage) - (newAscent - newAscent * shiftPercentage) * 1.3).toInt()
    }

    override fun updateMeasureState(tp: TextPaint) {
        updateDrawState(tp)
    }
}
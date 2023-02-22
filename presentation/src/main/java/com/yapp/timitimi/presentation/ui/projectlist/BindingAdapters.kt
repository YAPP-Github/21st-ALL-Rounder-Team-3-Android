package com.yapp.timitimi.presentation.ui.projectlist

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.yapp.timitimi.util.dp

@BindingAdapter("roundRadiusDp", "fillColor", "outlineColor", "outlineWidthDp", requireAll = false)
fun View.setGradientDrawable(
    roundRadiusDp: Float?,
    @ColorInt fillColor: Int?,
    @ColorInt outlineColor: Int?,
    outlineWidthDp: Float?
) {
    background = GradientDrawable().apply {
        roundRadiusDp?.let {
            this.cornerRadius = it.dp(context).toFloat()
        }
        fillColor?.let {
            this.setColor(it)
        }
        outlineColor?.let { outlineColor ->
            outlineWidthDp?.let {
                this.setStroke(it.dp(context), outlineColor, 0f, 0f)
            }
        }
    }
}
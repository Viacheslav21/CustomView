package com.example.customviewloader.loader.model

import android.graphics.Canvas
import android.graphics.Paint

abstract class GraphicObject {
    protected var paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    fun setWidth(width: Float) {
        paint.strokeWidth = width
    }


    abstract fun draw(canvas: Canvas)
}
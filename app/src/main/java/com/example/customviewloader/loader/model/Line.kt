package com.example.customviewloader.loader.model

import android.graphics.Canvas
import android.graphics.PointF

class Line : GraphicObject() {
    var point1: PointF? = null
    var point2: PointF? = null

    override fun draw(canvas: Canvas) {
        canvas.drawLine(point1!!.x, point1!!.y, point2!!.x, point2!!.y, paint)
    }

}
package com.example.customviewloader.loader.model

import android.animation.ValueAnimator
import android.graphics.*
import com.example.customviewloader.loader.LoaderView


class Pulse : LoaderView() {
    private val lines: Array<Line>
    private var lineWidth: Float = 10F
    private var lineDistance: Float = 0F
    private val numberOfLines: Int = 4
    private val myPaint = Paint()
    private var cornerSquareRadius = 12F

    init {
        lines = Array(numberOfLines) { Line() }
    }

    override fun initializeObjects() {
        val w = width - 50
        lineWidth = (w / (2 * numberOfLines)).toFloat()
        lineDistance = (w / (2 * numberOfLines)).toFloat()
        val firstX = (width - (lineWidth * numberOfLines + lineDistance * (numberOfLines - 1))) / 2f + lineWidth / 2f
        for (i in 0 until numberOfLines) {
            lines[i] = Line()
            lines[i].setWidth(lineWidth)
            lines[i].setColor(color[i])
            lines[i].point1 = PointF(firstX, center.y - height / 4f)
            lines[i].point2 = PointF(firstX, center.y + height / 4f)
        }
    }

    override fun setUpAnimation() {
        for (i in 0 until numberOfLines) {
            val fadeAnimator = ValueAnimator.ofInt(126, 255, 126)
            fadeAnimator.repeatCount = ValueAnimator.INFINITE
            fadeAnimator.duration = 1000
            fadeAnimator.startDelay = (i * 120).toLong()
            fadeAnimator.addUpdateListener { animation ->
                lines[i].setAlpha(animation.animatedValue as Int)
                invalidateListener?.reDraw()
            }
            fadeAnimator.start()
        }
    }

    override fun draw(canvas: Canvas) {
        myPaint.color = backgroundColor!!
        myPaint.strokeWidth = 10F
        canvas.drawRoundRect(RectF(0F, 0F, width.toFloat(), height.toFloat()), cornerSquareRadius, cornerSquareRadius, myPaint)

        for (i in 0 until numberOfLines) {
            canvas.save()
            canvas.translate(i * (lineWidth + lineDistance), 0f)
            lines[i].draw(canvas)
            canvas.restore()
        }

    }
}

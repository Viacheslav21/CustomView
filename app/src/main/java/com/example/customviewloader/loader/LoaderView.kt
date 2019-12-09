package com.example.customviewloader.loader

import android.graphics.Canvas
import android.graphics.PointF
import com.example.customviewloader.loader.callback.InvalidateListener


abstract class LoaderView {

    protected lateinit var color: IntArray
    protected var backgroundColor: Int? = null
    protected var width: Int = 0
    protected var height: Int = 0
    var desiredWidth: Int = 0
    var desiredHeight: Int = 0


    protected lateinit var center: PointF
    protected var invalidateListener: InvalidateListener? = null

    val isDetached: Boolean
        get() = invalidateListener == null

    init {
        this.desiredWidth = 200
        this.desiredHeight = 200
    }

    fun setCustomColor(color: IntArray) {
        this.color = color
    }

    fun setBackgroundColor(backgroundColor: Int) {
        this.backgroundColor = backgroundColor
    }

    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
        this.center = PointF(width / 2.0f, height / 2.0f)
    }

    fun setCustomInvalidateListener(invalidateListener: InvalidateListener) {
        this.invalidateListener = invalidateListener
    }

    abstract fun initializeObjects()

    abstract fun setUpAnimation()

    abstract fun draw(canvas: Canvas)

    fun onDetach() {
        if (invalidateListener != null) {
            invalidateListener = null
        }
    }
}

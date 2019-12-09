package com.example.customviewloader.loader

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.customviewloader.R
import com.example.customviewloader.loader.callback.InvalidateListener
import com.example.customviewloader.loader.model.Pulse

class CustomLoader : View, InvalidateListener {

    private var loaderView: LoaderView? = null

    private val progressColorList = intArrayOf(
            ContextCompat.getColor(context, R.color.loader_first),
            ContextCompat.getColor(context, R.color.loader_second),
            ContextCompat.getColor(context, R.color.loader_third),
            ContextCompat.getColor(context, R.color.loader_fourth))

    private  val backgroundColor= ContextCompat.getColor(context, R.color.loader_background)

    constructor(context: Context) : super(context) {
        initialize(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context, attrs)
    }

    private fun initialize(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLoader)

        val backGroundColor = typedArray.getColor(R.styleable.CustomLoader_backgroundColor, backgroundColor)

        loaderView = Pulse()
        loaderView!!.setCustomColor(progressColorList)
        loaderView!!.setBackgroundColor(backGroundColor)
        typedArray.recycle()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredWidth = resolveSize(loaderView!!.desiredWidth, widthMeasureSpec)
        val measuredHeight = resolveSize(loaderView!!.desiredHeight, heightMeasureSpec)

        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        loaderView?.setSize(width, height)
        loaderView?.initializeObjects()
        loaderView?.setUpAnimation()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        loaderView?.draw(canvas)
    }

    override fun reDraw() {
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (loaderView != null && loaderView!!.isDetached) {
            loaderView?.setCustomInvalidateListener(this)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        loaderView?.onDetach()

    }
}


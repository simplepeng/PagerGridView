package me.simple.pager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.absoluteValue

open class PagerGridViewPager2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val viewPager2 = ViewPager2(context)

    init {
        addView(viewPager2)
    }

    fun <VH : PagerGridView.ItemViewHolder> setAdapter(adapter: PagerGridView.ItemAdapter<VH>) {
        viewPager2.adapter = ViewPagerAdapter(adapter)
    }

    private fun canChildScroll(direction: Int): Boolean {
        return viewPager2.canScrollHorizontally(direction)
    }

    private var downX = 0f
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        handleInterceptTouchEvent(ev)
//        return super.onInterceptTouchEvent(ev)
//    }

    private fun handleInterceptTouchEvent(ev: MotionEvent) {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = ev.x - downX
                val scaledDx = moveX.absoluteValue * .5f

//                if (scaledDx > touchSlop) {
                if (canChildScroll(-moveX.toInt())) {
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
//                }
            }
            MotionEvent.ACTION_UP -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
    }
}
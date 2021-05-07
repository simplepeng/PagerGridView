package me.simple.pager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlin.math.absoluteValue
import kotlin.math.max

open class PagerGridViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (childCount == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }

        var maxHeight = 0
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childHeight = child.measuredHeight
            maxHeight = max(childHeight, maxHeight)
        }

        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, maxHeight)
    }

    fun <VH : PagerGridView.ItemViewHolder> setAdapter(adapter: PagerGridView.ItemAdapter<VH>) {
        this.adapter = InnerPagerAdapter(adapter)
    }

    private var downX = 0f
    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

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
                    if (canScrollHorizontally(-moveX.toInt())) {
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

    internal class InnerPagerAdapter<VH : PagerGridView.ItemViewHolder>(
        itemAdapter: PagerGridView.ItemAdapter<VH>
    ) : PagerAdapter() {

        private val pagerGridViewAdapter = ViewPagerAdapter(itemAdapter)

        override fun getCount(): Int {
            return pagerGridViewAdapter.itemCount
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val viewHolder = pagerGridViewAdapter.onCreateViewHolder(container, position)
            container.addView(viewHolder.itemView)
            pagerGridViewAdapter.onBindViewHolder(viewHolder, position)
            return viewHolder.itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            val view = obj as View
            container.removeView(view)
        }
    }
}
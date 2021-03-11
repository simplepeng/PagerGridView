package me.simple.pager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlin.math.max

class PagerGridViewPager @JvmOverloads constructor(
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

    internal class InnerPagerAdapter<VH : PagerGridView.ItemViewHolder>(
        itemAdapter: PagerGridView.ItemAdapter<VH>
    ) : PagerAdapter() {

        private val pagerGridViewAdapter = PagerGridViewAdapter(itemAdapter)

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
package me.simple.pager

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2

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


}
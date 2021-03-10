package me.simple.pager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class PagerGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mViewPager = ViewPager2(context)

    init {
        addView(mViewPager)
    }

    fun <VH : ItemViewHolder> setAdapter(adapter: ItemAdapter<VH>) {
        mViewPager.adapter = PagerGridViewAdapter(adapter)
    }

    abstract class ItemAdapter<VH : ItemViewHolder> {

        abstract fun getSpanCount(): Int

        abstract fun getLineCount(): Int

        abstract fun getItemCount(): Int

        abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, adapterPosition: Int)
    }

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
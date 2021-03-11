package me.simple.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal class PagerGridViewAdapter<VH : PagerGridView.ItemViewHolder>(
    private val itemAdapter: PagerGridView.ItemAdapter<VH>,
) : RecyclerView.Adapter<PagerGridViewAdapter.ViewHolder>() {

    private val mItemCount = itemAdapter.getItemCount()
    private val mSpanCount = itemAdapter.getSpanCount()
    private val mLineCount = itemAdapter.getLineCount()

    private val mPageGridCount = mSpanCount * mLineCount

    override fun getItemCount(): Int {
        if (itemAdapter.getItemCount() == 0) return 0

        return (mItemCount / mSpanCount / mLineCount) + 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_view_pager, parent, false)

        val viewHolder = ViewHolder(itemView)
        viewHolder.mRecyclerView.layoutManager = GridLayoutManager(parent.context, mSpanCount)

        return viewHolder
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
//        val pageIndex = holder.adapterPosition
        val pageIndex = position

        val itemCount = getPageItemCount(pageIndex)

        holder.mRecyclerView
            .adapter = PagerGridViewItemAdapter(mPageGridCount, itemCount, pageIndex, itemAdapter)
    }

    private fun getPageItemCount(pageIndex: Int): Int {
        return if (pageIndex == this.itemCount - 1) {
            if (pageIndex == 0) {
                mItemCount
            } else {
                mItemCount % mPageGridCount
            }
        } else {
            mPageGridCount
        }
    }


    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val mRecyclerView = itemView as RecyclerView

    }
}
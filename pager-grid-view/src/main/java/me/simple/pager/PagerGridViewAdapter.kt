package me.simple.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagerGridViewAdapter<VH : PagerGridView.ItemViewHolder>(
    private val itemAdapter: PagerGridView.ItemAdapter<VH>,
) : RecyclerView.Adapter<PagerGridViewAdapter.ViewHolder>() {

    private val mItemCount = itemAdapter.getItemCount()
    private val mSpanCount = itemAdapter.getSpanCount()
    private val mLineCount = itemAdapter.getLineCount()

    private val mPageItemCount = mSpanCount * mLineCount

    override fun getItemCount(): Int {
        if (itemAdapter.getItemCount() == 0) return 0

        return (itemAdapter.getItemCount() / mSpanCount / mLineCount) + 1
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
        val pageIndex = holder.adapterPosition

        val itemCount = if (pageIndex == itemCount - 1) {
            if (pageIndex == 0) {
                mItemCount
            } else {
                mItemCount % mPageItemCount
            }
        } else {
            mPageItemCount
        }

        holder.mRecyclerView
            .adapter = PagerGridViewItemAdapter(mPageItemCount, itemCount, pageIndex, itemAdapter)
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val mRecyclerView = itemView as RecyclerView

    }
}
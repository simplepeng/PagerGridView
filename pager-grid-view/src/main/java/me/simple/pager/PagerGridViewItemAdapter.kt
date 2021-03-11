package me.simple.pager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

internal class PagerGridViewItemAdapter<VH : PagerGridView.ItemViewHolder>(
    private val pageGridCount: Int,
    private val itemCount: Int,
    private val pageIndex: Int,
    private val itemAdapter: PagerGridView.ItemAdapter<VH>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        return itemAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        val realPosition = (pageIndex * pageGridCount) + holder.adapterPosition
        itemAdapter.onBindViewHolder(holder, realPosition)
    }
}
package me.simple.pager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PagerGridView {

    abstract class ItemAdapter<VH : ItemViewHolder> {

        abstract fun getSpanCount(): Int

        abstract fun getLineCount(): Int

        abstract fun getItemCount(): Int

        abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

        abstract fun onBindViewHolder(holder: VH, adapterPosition: Int)
    }

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
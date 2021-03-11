package demo.simple.pagergridview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import me.simple.pager.PagerGridView

class InnerItemAdapter(
    private val mItems: List<String>
) : PagerGridView.ItemAdapter<InnerViewHolder>() {

    override fun getSpanCount() = 4

    override fun getLineCount() = 4

    override fun getItemCount() = mItems.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return InnerViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: InnerViewHolder,
        adapterPosition: Int
    ) {
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, mItems[adapterPosition], Toast.LENGTH_SHORT)
                .show()
        }
        holder.tvItem.text = mItems[adapterPosition]
    }

}

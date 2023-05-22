package demo.simple.pagergridview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import me.simple.pager.PagerGridView

class InnerItemAdapter(
    private val mItems: List<String>,
    private val spanCount: Int = 4,
    private val lineCount: Int = 4
) : PagerGridView.ItemAdapter<InnerViewHolder>() {

    //列数
    override fun getSpanCount() = spanCount

    //行数
    override fun getLineCount() = lineCount

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
        //不要使用这个方法获取position，直接使用上面的`adapterPosition`
//        holder.adapterPosition

        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, mItems[adapterPosition], Toast.LENGTH_SHORT)
                .show()
        }
        holder.tvItem.text = mItems[adapterPosition]
    }
}


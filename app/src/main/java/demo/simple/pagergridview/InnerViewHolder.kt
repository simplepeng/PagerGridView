package demo.simple.pagergridview

import android.view.View
import android.widget.TextView
import me.simple.pager.PagerGridView

class InnerViewHolder(itemView: View) : PagerGridView.ItemViewHolder(itemView) {

    val tvItem = itemView.findViewById<TextView>(R.id.tvItem)

}
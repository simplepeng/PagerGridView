package demo.simple.pagergridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.simple.pager.PagerGridView

class MainActivity : AppCompatActivity() {

    private val pagerGridView by lazy { findViewById<PagerGridView>(R.id.pagerGridView) }

    private val mItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (index in 0 until 100) {
            mItems.add(index.toString())
        }

        pagerGridView.setAdapter(InnerItemAdapter())
    }

    private inner class InnerItemAdapter : PagerGridView.ItemAdapter<InnerViewHolder>() {

        override fun getSpanCount() = 4

        override fun getLineCount() = 4

        override fun getItemCount(): Int {
            return mItems.size
        }

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
            holder.tvItem.text = mItems[adapterPosition]
        }


    }

    inner class InnerViewHolder(itemView: View) : PagerGridView.ItemViewHolder(itemView) {

        val tvItem = itemView.findViewById<TextView>(R.id.tvItem)

    }
}
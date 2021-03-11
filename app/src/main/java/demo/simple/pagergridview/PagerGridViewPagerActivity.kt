package demo.simple.pagergridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rd.PageIndicatorView
import me.simple.pager.PagerGridViewPager

class PagerGridViewPagerActivity : AppCompatActivity() {

    private val pagerGridViewPager by lazy { findViewById<PagerGridViewPager>(R.id.pagerGridViewPager) }
    private val indicatorView2 by lazy { findViewById<PageIndicatorView>(R.id.indicatorView2) }

    private val mItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager_grid_view_pager)
        title = "PagerGridViewPager"

        for (index in 0 until 50) {
            mItems.add(index.toString())
        }

        pagerGridViewPager.setAdapter(InnerItemAdapter(mItems))
        indicatorView2.setViewPager(pagerGridViewPager)
    }


}
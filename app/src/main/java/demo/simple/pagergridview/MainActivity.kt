package demo.simple.pagergridview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.rd.PageIndicatorView
import me.simple.pager.PagerGridViewPager2

class MainActivity : AppCompatActivity() {

    private val pagerGridView by lazy { findViewById<PagerGridViewPager2>(R.id.pagerGridView) }
    private val indicatorView by lazy { findViewById<PageIndicatorView>(R.id.indicatorView) }

    private val btnPagerGridViewPager by lazy { findViewById<View>(R.id.btnPagerGridViewPager) }

    private val mItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "PagerGridViewPager2"

        btnPagerGridViewPager.setOnClickListener {
            startActivity(Intent(this, PagerGridViewPagerActivity::class.java))
        }

        for (index in 0 until ITEM_COUNT) {
            mItems.add(index.toString())
        }


        pagerGridView.setAdapter(InnerItemAdapter(mItems))

        indicatorView.count = pagerGridView.viewPager2.adapter?.itemCount ?: 0
        pagerGridView.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorView.setSelected(position)
            }
        })

    }

    companion object{
        const val ITEM_COUNT = 70
    }

}
package demo.simple.pagergridview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.rd.PageIndicatorView
import me.simple.pager.PagerGridViewPager2

class MainActivity : AppCompatActivity() {

    private val pagerGridViewPager2 by lazy { findViewById<PagerGridViewPager2>(R.id.pagerGridViewPager2) }
    private val indicatorView by lazy { findViewById<PageIndicatorView>(R.id.indicatorView) }

    private val btnPagerGridViewPager by lazy { findViewById<View>(R.id.btnPagerGridViewPager) }

    private val btnTouch by lazy { findViewById<View>(R.id.btnTouch) }

    private val mItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "PagerGridViewPager2"

        btnPagerGridViewPager.setOnClickListener {
            startActivity(Intent(this, PagerGridViewPagerActivity::class.java))
        }

        btnTouch.setOnClickListener {
            startActivity(Intent(this, TestNestTouchActivity::class.java))
        }

        for (index in 0 until ITEM_COUNT) {
            mItems.add(index.toString())
        }


        pagerGridViewPager2.setAdapter(InnerItemAdapter(mItems))

        indicatorView.count = pagerGridViewPager2.viewPager2.adapter?.itemCount ?: 0
        pagerGridViewPager2.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorView.setSelected(position)
            }
        })

    }

    companion object {
        const val ITEM_COUNT = 70
    }

}
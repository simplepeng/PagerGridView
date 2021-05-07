package demo.simple.pagergridview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import me.simple.pager.PagerGridViewPager

class TestNestTouchActivity : AppCompatActivity() {

    private val mViewPager2 by lazy { findViewById<ViewPager2>(R.id.viewPager2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_nest_touch)

//        mViewPager2.isUserInputEnabled = false
        mViewPager2.adapter = VpAdapter(this)
    }

    class VpAdapter(
        fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 1
        }

        override fun createFragment(position: Int): Fragment {
            return VpFragment()
        }

    }

    class VpFragment : Fragment() {

        private val mItems = mutableListOf<String>()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_vp, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            for (index in 0 until MainActivity.ITEM_COUNT) {
                mItems.add(index.toString())
            }

            val pagerGridViewPager = view.findViewById<PagerGridViewPager>(R.id.pagerGridViewPager)
            pagerGridViewPager.setAdapter(InnerItemAdapter(mItems, 3))
        }
    }
}
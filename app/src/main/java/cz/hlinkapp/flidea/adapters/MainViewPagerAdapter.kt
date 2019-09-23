package cz.hlinkapp.flidea.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import cz.hlinkapp.flidea.fragments.FlideaFragment

/**
 * A [androidx.viewpager.widget.ViewPager] adapter for the main screen. Contains a fixed number of 5 [FlideaFragment] fragment.
 */
class MainViewPagerAdapter (fm: FragmentManager, private val appName: String): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence? {
        return appName
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> FlideaFragment().apply { arguments = Bundle().apply { putInt(FlideaFragment.ARG_FRAG_INDEX,0) } }
        1 -> FlideaFragment().apply { arguments = Bundle().apply { putInt(FlideaFragment.ARG_FRAG_INDEX,1) } }
        2 -> FlideaFragment().apply { arguments = Bundle().apply { putInt(FlideaFragment.ARG_FRAG_INDEX,2) } }
        3 -> FlideaFragment().apply { arguments = Bundle().apply { putInt(FlideaFragment.ARG_FRAG_INDEX,3) } }
        4 -> FlideaFragment().apply { arguments = Bundle().apply { putInt(FlideaFragment.ARG_FRAG_INDEX,4) } }
        else -> throw IllegalArgumentException()
    }

    override fun getCount(): Int = 5
}
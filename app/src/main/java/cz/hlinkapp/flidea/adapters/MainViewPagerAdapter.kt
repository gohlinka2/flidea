package cz.hlinkapp.flidea.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import cz.hlinkapp.flidea.fragments.FlideaFragment

/**
 * A [androidx.viewpager.widget.ViewPager] adapter for the main screen. Contains a fixed number of 5 [FlideaFragment] fragment.
 */
class MainViewPagerAdapter (fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = FlideaFragment.createInstance(position)

    override fun getCount(): Int = 5
}
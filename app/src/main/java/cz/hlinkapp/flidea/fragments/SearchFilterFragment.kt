package cz.hlinkapp.flidea.fragments

import cz.hlinkapp.flidea.R
import cz.hlinkapp.gohlinka2_utils2.fragments.abstraction.BaseFragment

class SearchFilterFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_search_filter

    companion object {
        const val TAG = "SearchFilterFragment"
    }
}
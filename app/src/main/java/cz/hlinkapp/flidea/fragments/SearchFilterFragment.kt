package cz.hlinkapp.flidea.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.activities.PickAirportActivity
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.model.SearchFilters
import cz.hlinkapp.flidea.utils.SharedPrefHelper
import cz.hlinkapp.gohlinka2_utils2.fragments.abstraction.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_filter.*
import javax.inject.Inject

class SearchFilterFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_search_filter

    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper

    private lateinit var mSearchFilters : SearchFilters

    override fun initDependencies(savedInstanceState: Bundle?) {
        super.initDependencies(savedInstanceState)

        (activity?.applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        mSearchFilters = sharedPrefHelper.getSearchFilters()

        departureAirport.text = mSearchFilters.airport.name
        numberOfPassengers.text = mSearchFilters.passengers.toString()

        addButton.setOnClickListener {
            val pax = mSearchFilters.passengers
            if (pax + 1 <= 9) {
                mSearchFilters.passengers++
                numberOfPassengers.text = mSearchFilters.passengers.toString()
                saveSearchFilters()
            }
        }

        removeButton.setOnClickListener {
            val pax = mSearchFilters.passengers
            if (pax - 1 >= 1) {
                mSearchFilters.passengers--
                numberOfPassengers.text = mSearchFilters.passengers.toString()
                saveSearchFilters()
            }
        }

        departureAirport.setOnClickListener {
            startActivityForResult(Intent(context,PickAirportActivity::class.java), PICK_AIRPORT_ACTION_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AIRPORT_ACTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            mSearchFilters.airport.name = data.getStringExtra(PickAirportActivity.KEY_AIRPORT_NAME)
            mSearchFilters.airport.id = data.getStringExtra(PickAirportActivity.KEY_AIRPORT_ID)
            departureAirport.text = mSearchFilters.airport.name
            saveSearchFilters()
        }
    }

    /**
     * Saves the current search filters.
     */
    private fun saveSearchFilters() {
        sharedPrefHelper.saveSearchFilters(mSearchFilters)
    }

    companion object {
        const val TAG = "SearchFilterFragment"

        const val PICK_AIRPORT_ACTION_CODE = 12345
    }
}
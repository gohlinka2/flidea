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

/**
 * A fragment which displays the current search filters and allows the user to change them.
 * Designed to be displayed as a backdrop's back layer.
 * @see {https://material.io/components/backdrop}
 */
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

        //load previously saved filters
        mSearchFilters = sharedPrefHelper.getSearchFilters()
        updateViews()

        addButton.setOnClickListener {
            val pax = mSearchFilters.passengers
            if (pax + 1 <= 9) {
                mSearchFilters.passengers++
                saveSearchFilters()
                updateViews()
            }
        }

        removeButton.setOnClickListener {
            val pax = mSearchFilters.passengers
            if (pax - 1 >= 1) {
                mSearchFilters.passengers--
                saveSearchFilters()
                updateViews()
            }
        }

        departureAirport.setOnClickListener { startActivityForResult(Intent(context,PickAirportActivity::class.java), PICK_AIRPORT_ACTION_CODE) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AIRPORT_ACTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            mSearchFilters.airport.name = data.getStringExtra(PickAirportActivity.KEY_AIRPORT_NAME)
            mSearchFilters.airport.id = data.getStringExtra(PickAirportActivity.KEY_AIRPORT_ID)
            saveSearchFilters()
            updateViews()
        }
    }

    /**
     * Sets the views to display current data from [mSearchFilters].
     */
    private fun updateViews() {
        departureAirport.text = mSearchFilters.airport.name
        numberOfPassengers.text = mSearchFilters.passengers.toString()
    }

    /**
     * Saves the current search filters from [mSearchFilters] to shared prefs.
     */
    private fun saveSearchFilters() {
        sharedPrefHelper.saveSearchFilters(mSearchFilters)
    }

    companion object {
        const val TAG = "SearchFilterFragment"

        //Activity result code
        const val PICK_AIRPORT_ACTION_CODE = 12345
    }
}
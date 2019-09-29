package cz.hlinkapp.flidea.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.hlinkapp.flidea.data.repositories.LocationSearchRepository
import cz.hlinkapp.flidea.model.Airport
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

/**
 * A ViewModel for [PickAirportActivity].
 */
class LocationSearchViewModel @Inject constructor(repository: LocationSearchRepository): ViewModel() {

    private val mRepository : LocationSearchRepository = repository

    private var mAirportResults : LiveData<List<Airport>>? = null

    /**
     * The LiveData with airport search results.
     */
    val airportResults : LiveData<List<Airport>>? get() = mAirportResults

    /**
     * An observable status of the airport results download task.
     */
    val airportResultsStatus : LiveData<RequestInfo> get() = mRepository.airportResultsStatus


    /**
     * Start the search, for flights with the search [query].
     * Call before accessing [airportResults].
     */
    fun searchAirports(query: String) {
        mAirportResults = mRepository.searchAirports(query)
    }
}
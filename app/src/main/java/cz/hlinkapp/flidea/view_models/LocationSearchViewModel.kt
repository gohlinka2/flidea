package cz.hlinkapp.flidea.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.hlinkapp.flidea.data.repositories.LocationSearchRepository
import cz.hlinkapp.flidea.model.Airport
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject
import cz.hlinkapp.flidea.activities.PickAirportActivity


/**
 * A ViewModel for [PickAirportActivity].
 */
class LocationSearchViewModel @Inject constructor(repository: LocationSearchRepository): ViewModel() {

    private val mRepository : LocationSearchRepository = repository

    /**
     * A LiveData with a list of airports returned from the search task.
     */
    val airportResults : LiveData<List<Airport>?> get() = mRepository.airportResults

    /**
     * An observable status of the airport results download task.
     */
    val airportResultsStatus : LiveData<RequestInfo> get() = mRepository.airportResultsStatus


    /**
     * Start the search for locations containing the search [query].
     * Call before accessing [airportResults].
     */
    fun searchAirports(query: String) {
        mRepository.searchLocations(query)
    }
}
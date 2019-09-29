package cz.hlinkapp.flidea.data.repositories

import androidx.lifecycle.LiveData
import cz.hlinkapp.flidea.model.Airport
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo

class LocationSearchRepository {
    fun searchAirports(query: String): LiveData<List<Airport>>? {
        TODO()
    }

    val airportResultsStatus: LiveData<RequestInfo> = TODO()
}

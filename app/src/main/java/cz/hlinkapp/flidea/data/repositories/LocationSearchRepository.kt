package cz.hlinkapp.flidea.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.data.data_sources.server.SkypickerService
import cz.hlinkapp.flidea.model.Airport
import cz.hlinkapp.flidea.model.LocationSearchRootApiResponse
import cz.hlinkapp.gohlinka2_utils2.utils.ConnectivityChecker
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * A repository for getting location search results.
 *
 * Note:
 * This repository does not dedicate network logic to a ServerDataSource, but handles it here,
 * because there's no db involved and a dedicated ServerDataSource would be a redundant layer of abstraction in this scenario.
 */
class LocationSearchRepository @Inject constructor(
    connectivityChecker: ConnectivityChecker,
    skypickerService: SkypickerService
) {

    private val mConnectivityChecker = connectivityChecker
    private val mSkypickerService = skypickerService

    private val mAirportResultsStatus = MutableLiveData<RequestInfo>(RequestInfo.notStarted())
    private val mAirportResults = MutableLiveData<List<Airport>?>(null)

    /**
     * An observable status of the airport results download task.
     */
    val airportResultsStatus: LiveData<RequestInfo> get() = mAirportResultsStatus
    /**
     * A LiveData with a list of airports returned from the search task.
     */
    val airportResults : LiveData<List<Airport>?> = mAirportResults

    /**
     * Makes a network call to search for locations containing the [query].
     * You can observe the status of the task using [airportResultsStatus].
     * Does not run the task if a different task is already running or there is no internet.
     */
    fun searchLocations(query: String) {
        val status = mAirportResultsStatus
        if (status.value?.isProcessing() == false) if (mConnectivityChecker.isConnected()) {
            status.value = RequestInfo.processing()
            mSkypickerService.getAirportSearchResults(ServerContract.createLocationSearchQueryParams(query)).enqueue(object :
                Callback<LocationSearchRootApiResponse> {
                override fun onFailure(call: Call<LocationSearchRootApiResponse>, t: Throwable) = status.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED))
                override fun onResponse(call: Call<LocationSearchRootApiResponse>, response: Response<LocationSearchRootApiResponse>) =
                    if (response.isSuccessful && response.body() != null) {
                        mAirportResults.postValue(response.body()!!.locations)
                        status.postValue(RequestInfo.done(RequestInfo.RequestResult.OK))
                    }
                    else status.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED)) })
        } else status.postValue(RequestInfo.done(RequestInfo.RequestResult.NO_INTERNET))
    }
}

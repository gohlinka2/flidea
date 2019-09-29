package cz.hlinkapp.flidea.data.repositories

import androidx.lifecycle.LiveData
import cz.hlinkapp.flidea.data.data_sources.room.MainDao
import cz.hlinkapp.flidea.data.data_sources.server.MainServerDataSource
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.flidea.utils.SharedPrefHelper
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

/**
 * A repository for handling data.
 * This is the class that decides from which source the data will be loaded and handles its refreshing.
 */
class MainRepository @Inject constructor(
    dao: MainDao,
    serverDataSource: MainServerDataSource,
    sharedPrefHelper: SharedPrefHelper
){

    private val mMainDao = dao
    private val mServerDataSource = serverDataSource
    private val mSharedPrefHelper = sharedPrefHelper

    /**
     * An observable status of the flights download task.
     */
    val flightsStatus: LiveData<RequestInfo> get() = mServerDataSource.flightsStatus

    /**
     * Returns a LiveData with flights from the DB. Also refreshes the data if the right conditions are met.
     * You can observe the status of the network call using [flightsStatus].
     */
    fun getFlights(): LiveData<List<Flight>>? {
        mServerDataSource.refreshFlights()
        return mMainDao.getFlightsForDisplay()
    }

    /**
     * Reruns the refreshing process of flights.
     * Call if the previous request has failed and the user wants to try again.
     * Doesn't force the data to be refreshed if it has already been successfully done today.
     */
    fun retryRefreshingFlights() {
        mServerDataSource.refreshFlights()
    }

    /**
     * Invalidates the currently saved flights and downloads new ones.
     * Call after the search filters have changed.
     */
    fun invalidateData() {
        mMainDao.invalidateData()
        mSharedPrefHelper.invalidateLastFetchedDay()
        mServerDataSource.refreshFlights()
    }
}
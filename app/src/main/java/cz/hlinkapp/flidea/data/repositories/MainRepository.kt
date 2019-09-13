package cz.hlinkapp.flidea.data.repositories

import androidx.lifecycle.LiveData
import cz.hlinkapp.flidea.data.data_sources.room.MainDao
import cz.hlinkapp.flidea.data.data_sources.server.ServerDataSource
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

/**
 * A repository for handling data.
 * This is the class that decides from which source the data will be loaded and handles its refreshing.
 */
class MainRepository @Inject constructor(
    dao: MainDao,
    serverDataSource: ServerDataSource
){

    private val mMainDao = dao
    private val mServerDataSource = serverDataSource

    val flightsStatus: LiveData<RequestInfo> get() = mServerDataSource.flightsStatus

    /**
     * Returns a LiveData with flights from the DB. Also refreshes the data if the right conditions are met.
     * You can observe the status of the network call using [flightsStatus].
     */
    fun getFlights(): LiveData<List<Flight>>? {
        mServerDataSource.refreshFlights()
        return mMainDao.getFlightsForDisplay()
    }

    /*fun forceRefreshFlights(): LiveData<List<Flight>>? {

    }*/
}
package cz.hlinkapp.flidea.data.repositories

import Flight
import androidx.lifecycle.LiveData
import cz.hlinkapp.flidea.data.data_sources.room.MainDao
import cz.hlinkapp.flidea.data.data_sources.server.ServerDataSource
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

class MainRepository @Inject constructor(
    dao: MainDao,
    serverDataSource: ServerDataSource
){

    private val mMainDao = dao
    private val mServerDataSource = serverDataSource

    val flightsStatus: LiveData<RequestInfo> get() = mServerDataSource.flightsStatus

    fun getFlights(): LiveData<List<Flight>>? {
        mServerDataSource.refreshFlights()
        return mMainDao.getFlightsForDisplay()
    }

    /*fun forceRefreshFlights(): LiveData<List<Flight>>? {

    }*/
}
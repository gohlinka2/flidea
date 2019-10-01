package cz.hlinkapp.flidea.data.data_sources.server

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.data.data_sources.room.MainDao
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.flidea.model.RootApiResponse
import cz.hlinkapp.flidea.utils.SharedPrefHelper
import cz.hlinkapp.gohlinka2_utils2.utils.ConnectivityChecker
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import cz.hlinkapp.gohlinka2_utils2.utils.getStartOfDayTimestamp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class for main network calls. Fetches new flight data and handles its saving into the db.
 */
@Singleton
class MainServerDataSource @Inject constructor(
    executor: Executor,
    dao: MainDao,
    connectivityChecker: ConnectivityChecker,
    skypickerService: SkypickerService,
    sharedPrefHelper: SharedPrefHelper
) {

    private val mSharedPrefHelper = sharedPrefHelper
    private val mExecutor = executor
    private val mDao = dao
    private val mConnectivityChecker = connectivityChecker
    private val mSkypickerService = skypickerService

    private val mFlightsStatus = MutableLiveData<RequestInfo>(RequestInfo.notStarted())

    /**
     * An observable status of the flights download task.
     */
    val flightsStatus: LiveData<RequestInfo> get() = mFlightsStatus

    /**
     * Downloads new flight ideas from the server, saves the to the db and marks the new flights to be displayed.
     * Only executes the task when there is access to internet, no previous task is running and the data hasn't been downloaded yet today.
     */
    fun refreshFlights() {
        if ( mSharedPrefHelper.shouldFetchNewData() && mFlightsStatus.value?.isProcessing() == false) if (mConnectivityChecker.isConnected()) {
            mFlightsStatus.value = RequestInfo.processing()
            val map = ServerContract.createFlightQueryParams(mSharedPrefHelper.getSearchFilters())
            mSkypickerService.getFlights(map).enqueue(object : Callback<RootApiResponse> {
                override fun onFailure(call: Call<RootApiResponse>, t: Throwable) = mFlightsStatus.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED))
                override fun onResponse(call: Call<RootApiResponse>, response: Response<RootApiResponse>) =
                    if (response.isSuccessful && response.body() != null && response.body()!!.data.isNotEmpty()) {
                        mExecutor.execute {
                            if (handleFlightSaving(response.body()!!)) mFlightsStatus.postValue(RequestInfo.done(RequestInfo.RequestResult.OK))
                            else mFlightsStatus.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED))
                        } } else mFlightsStatus.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED)) })
        } else mFlightsStatus.postValue(RequestInfo.done(RequestInfo.RequestResult.NO_INTERNET))
    }

    /**
     * Handles the flight saving according to [MainDao]'s usage flowchart.
     * @return Whether the operation was successful.
     */
    fun handleFlightSaving(rootApiResponse: RootApiResponse) : Boolean{
        //Write control values to flights
        for (flight in rootApiResponse.data) {
            flight.currency = rootApiResponse.currency
            flight.fetched_timestamp = Calendar.getInstance().timeInMillis
            flight.display_day_timestamp = Flight.DISPLAY_DAY_TIMESTAMP_DEF_VAL
        }

        mDao.saveFlights(rootApiResponse.data)
        val ids = mDao.selectFlightIdsForDisplay()
        if (ids.isEmpty()) return false
        mDao.markFlightsForDisplay(getStartOfDayTimestamp(),ids)
        mSharedPrefHelper.saveLastFetchedDay()
        return true
    }
}
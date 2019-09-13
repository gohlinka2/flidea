package cz.hlinkapp.flidea.data.data_sources.server

import Flight.Companion.DISPLAY_DAY_TIMESTAMP_DEF_VAL
import RootApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_ASCENDING
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_CURRENCY
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_DATE_FROM
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_DATE_TO
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_FLIGHT_TYPE
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_FLY_FROM
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_LIMIT
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_LOCALE
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_NIGHTS_IN_DST_FROM
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_NIGHTS_IN_DST_TO
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_ONE_FOR_CITY
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_PARTNER
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_PASSENGERS
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.QP_SORT
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.VAL_ONE_FOR_CITY
import cz.hlinkapp.flidea.contracts.ServerContract.Companion.VAL_PARTNER
import cz.hlinkapp.flidea.data.data_sources.room.MainDao
import cz.hlinkapp.flidea.utils.SharedPrefHelper
import cz.hlinkapp.flidea.utils.getStartOfDayTimestamp
import cz.hlinkapp.gohlinka2_utils2.utils.ConnectivityChecker
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class ServerDataSource @Inject constructor(
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

    val flightsStatus: LiveData<RequestInfo> get() = mFlightsStatus

    fun refreshFlights() {
        val status = mFlightsStatus
        if ( mSharedPrefHelper.shouldFetchNewData() && status.value?.isProcessing() == false) if (mConnectivityChecker.isConnected()) {
            status.postValue(RequestInfo.processing())
            val cal = Calendar.getInstance()
            val map = HashMap<String, Any>()
            //TODO: change hardcoded values
            map[QP_FLY_FROM] = "PRG"
            map[QP_DATE_FROM] = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH + 1)}/${cal.get(Calendar.YEAR)}"
            cal.add(Calendar.DAY_OF_MONTH,14)
            map[QP_DATE_TO] = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH + 1)}/${cal.get(Calendar.YEAR)}"
            map[QP_NIGHTS_IN_DST_FROM] = 1
            map[QP_NIGHTS_IN_DST_TO] = 10
            map[QP_FLIGHT_TYPE] = "round"
            map[QP_PASSENGERS] = 1
            map[QP_PARTNER] = VAL_PARTNER
            map[QP_CURRENCY] = "CZK"
            map[QP_LOCALE] = "cz"
            map[QP_LIMIT] = 150
            map[QP_SORT] = "quality"
            map[QP_ASCENDING] = 0
            map[QP_ONE_FOR_CITY] = VAL_ONE_FOR_CITY
            mSkypickerService.getFlights(map).enqueue(object : Callback<RootApiResponse> {
                override fun onFailure(call: Call<RootApiResponse>, t: Throwable) = status.postValue(
                    RequestInfo.done(
                        RequestInfo.RequestResult.FAILED))
                override fun onResponse(call: Call<RootApiResponse>, response: Response<RootApiResponse>) =
                    if (response.isSuccessful && response.body() != null && response.body()!!.data.isNotEmpty()) {
                        mExecutor.execute {
                            for (flight in response.body()!!.data) {
                                flight.fetched_timestamp = Calendar.getInstance().timeInMillis
                                flight.display_day_timestamp = DISPLAY_DAY_TIMESTAMP_DEF_VAL
                            }
                            mDao.saveFlights(response.body()!!.data)
                            mDao.selectNewFlightsForDisplay(getStartOfDayTimestamp())
                            mSharedPrefHelper.saveLastFetchedDay()
                            status.postValue(RequestInfo.done(RequestInfo.RequestResult.OK))
                        } } else status.postValue(RequestInfo.done(RequestInfo.RequestResult.FAILED)) })
        } else status.postValue(RequestInfo.done(RequestInfo.RequestResult.NO_INTERNET))
    }
}
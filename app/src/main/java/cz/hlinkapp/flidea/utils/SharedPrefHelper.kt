package cz.hlinkapp.flidea.utils

import com.google.gson.Gson
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.model.SearchFilters
import cz.hlinkapp.gohlinka2_utils2.utils.SharedPrefUtil
import javax.inject.Inject

/**
 * A Shared Preferences util class containing several project-specific utility functions.
 */
class SharedPrefHelper @Inject constructor(sharedPrefUtil: SharedPrefUtil, gson: Gson) {

    private val mSharedPrefUtil = sharedPrefUtil
    private val mGson = gson

    /**
     * Updates the last-fetched-day timestamp to the current day.
     * Use when the data has just been fetched to prevent redundant repetitive network calls.
     */
    fun saveLastFetchedDay() = mSharedPrefUtil.setLongSharedPref(SP_KEY_LAST_FETCHED_DAY, getStartOfDayTimestamp())

    /**
     * Resets the last-fetched-day timestamp.
     * Use when the query parameters have changed and the data needs to be re-fetched in the near future.
     */
    fun invalidateLastFetchedDay() {
        mSharedPrefUtil.setLongSharedPref(SP_KEY_LAST_FETCHED_DAY,null)
    }

    /**
     * Determines whether the flights data should be refreshed.
     * @return True if the data was fetched on a different day than the current one.
     */
    fun shouldFetchNewData() : Boolean = getStartOfDayTimestamp() != mSharedPrefUtil.getLongSharedPref(SP_KEY_LAST_FETCHED_DAY)

    /**
     * Saves the SearchFilters object for later use.
     * Retrieve it with [getSearchFilters].
     */
    fun saveSearchFilters(filters: SearchFilters) {
        mSharedPrefUtil.setStringSharedPref(SP_KEY_SEARCH_FILTERS,mGson.toJson(filters))
    }

    /**
     * Retrieves the saved SearchFilters object, or creates a default one.
     * Save it with [saveSearchFilters].
     */
    fun getSearchFilters() : SearchFilters {
        val jsonString = mSharedPrefUtil.getStringSharedPref(SP_KEY_SEARCH_FILTERS)
        return if (jsonString != null && jsonString.isNotEmpty()) mGson.fromJson(jsonString,SearchFilters::class.java)
        else SearchFilters(passengers = 1, airportCode = ServerContract.DEFAULT_AIRPORT_CODE, airportName = ServerContract.DEFAULT_AIRPORT_CODE)
    }

    companion object {
        const val SP_KEY_LAST_FETCHED_DAY = "lat_fetched_day"
        const val SP_KEY_SEARCH_FILTERS = "searchFilters"
    }
}
package cz.hlinkapp.flidea.utils

import cz.hlinkapp.gohlinka2_utils2.utils.SharedPrefUtil
import javax.inject.Inject

/**
 * A Shared Preferences util class containing several project-specific utility functions.
 */
class SharedPrefHelper @Inject constructor(sharedPrefUtil: SharedPrefUtil) {

    private val mSharedPrefUtil = sharedPrefUtil

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

    companion object {
        const val SP_KEY_LAST_FETCHED_DAY = "lat_fetched_day"
    }
}
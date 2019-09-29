package cz.hlinkapp.flidea.data.data_sources.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.flidea.model.Flight.Companion.DISPLAY_DAY_TIMESTAMP_DEF_VAL

/**
 * Main Room's Data Access Object for accessing the db.
 *
 * Usage flowchart:
 * Call [getFlightsForDisplay], observe the LiveDate and display the flights -> download new flights if they haven't been downloaded today yet (use [SharedPrefHelper.shouldFetchNewData] to check) ->
 * -> [saveFlights] and call [SharedPrefHelper.saveLastFetchedDay] ->
 * -> call [selectFlightIdsForDisplay] and then use the returned IDs to call [markFlightsForDisplay] ->
 * -> LiveData refreshes and the UI displays the new flights.
 */
@Dao
interface MainDao {

    /**
     * Saves newly fetched flights to the db.
     * Ignores the rows that are conflicting - this is needed to prevent overwriting the data about flights that have been already displayed (display_day_timestamp) -> prevent displaying the same flights again.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFlights(flights: List<Flight>)

    /**
     * Deletes all flights. Call when the search filters have been changed.
     */
    @Query("DELETE FROM flight")
    fun deleteFlights()

    /**
     * Loads LiveData with flights to be displayed in UI.
     * This loads 5 flights ordered by display_day_timestamp in descending order (flights selected for display on this or recent days have the highest timestamp).
     */
    @Query("SELECT * FROM flight ORDER BY display_day_timestamp DESC LIMIT 5")
    fun getFlightsForDisplay() : LiveData<List<Flight>>

    /**
     * Loads list of IDs of flights that should be selected for display.
     * This loads 5 flights that haven't been displayed yet, ordered by the time they were downloaded, then quality, then popularity, in descending order.
     * This should be called after new flights have been downloaded and saved to the db. The IDs returned from this should be then used to call [markFlightsForDisplay].
     */
    @Query("SELECT id FROM flight WHERE display_day_timestamp =$DISPLAY_DAY_TIMESTAMP_DEF_VAL ORDER BY fetched_timestamp,quality,popularity DESC LIMIT 5")
    fun selectFlightIdsForDisplay() : List<String>

    /**
     * Marks the selected flights with the given [ids] to be displayed on the day [displayDayTimestamp] (set the display_day_timestamp to [displayDayTimestamp]).
     * Always pass the current day to [displayDayTimestamp].
     * This should be called after new flights have been downloaded and saved to the db, after the call to [selectFlightIdsForDisplay] and using the IDs returned from it.
     */
    @Query("UPDATE flight SET display_day_timestamp=:displayDayTimestamp WHERE id IN (:ids)")
    fun markFlightsForDisplay(displayDayTimestamp : Long, ids: List<String>)
}
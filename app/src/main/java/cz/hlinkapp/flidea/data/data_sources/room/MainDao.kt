package cz.hlinkapp.flidea.data.data_sources.room

import Flight
import Flight.Companion.DISPLAY_DAY_TIMESTAMP_DEF_VAL
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFlights(flights: List<Flight>)

    @Query("SELECT * FROM flight ORDER BY display_day_timestamp DESC LIMIT 5")
    fun getFlightsForDisplay() : LiveData<List<Flight>>

//    @Query("SELECT * FROM flight WHERE display_day_timestamp =$DISPLAY_DAY_TIMESTAMP_DEF_VAL ORDER BY fetched_timestamp,quality,popularity DESC LIMIT 5")
//    fun getFlightsForDisplayDaySelection() : LiveData<List<Flight>>

    @Query("UPDATE flight SET display_day_timestamp=:displayDayTimestamp WHERE display_day_timestamp =$DISPLAY_DAY_TIMESTAMP_DEF_VAL ORDER BY fetched_timestamp,quality,popularity DESC LIMIT 5")
    fun selectNewFlightsForDisplay(displayDayTimestamp: Long)
}
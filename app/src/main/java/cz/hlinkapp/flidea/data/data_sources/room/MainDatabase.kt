package cz.hlinkapp.flidea.data.data_sources.room

import Flight
import Route
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Main Room's [RoomDatabase].
 */
@Database(entities = [Flight::class,Route::class], version = 0, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao() : MainDao
}
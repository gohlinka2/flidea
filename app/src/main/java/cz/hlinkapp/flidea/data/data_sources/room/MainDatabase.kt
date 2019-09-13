package cz.hlinkapp.flidea.data.data_sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.flidea.model.Route

/**
 * Main Room's [RoomDatabase].
 */
@Database(entities = [Flight::class, Route::class], version = 0, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao() : MainDao
}
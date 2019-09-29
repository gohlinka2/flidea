package cz.hlinkapp.flidea.data.data_sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.flidea.utils.RoomTypeConverters

/**
 * Main Room's [RoomDatabase].
 */
@TypeConverters(RoomTypeConverters::class)
@Database(entities = [Flight::class], version = 3, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao() : MainDao
}
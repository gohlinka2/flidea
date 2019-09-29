package cz.hlinkapp.flidea.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.hlinkapp.flidea.model.Country
import cz.hlinkapp.flidea.model.Route
import javax.inject.Singleton

/**
 * A class with several [Room] [TypeConverter]s.
 */
@Singleton
class RoomTypeConverters {

    @TypeConverter
    fun listOfRouteToJson(input : List<Route>) : String {
        return mGson.toJson(input)
    }

    @TypeConverter
    fun listOfRouteFromJson(input : String) : List<Route> {
        return mGson.fromJson<List<Route>>(input)
    }

    @TypeConverter
    fun countryToJson(input : Country) : String {
        return mGson.toJson(input)
    }

    @TypeConverter
    fun countryFromJson(input : String) : Country {
        return mGson.fromJson<Country>(input)
    }

    companion object {
        var mGson : Gson = GsonBuilder().serializeNulls().create()
    }
}
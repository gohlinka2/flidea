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
    fun listOfStringToJson(input : List<String>) : String {
        return mGson.toJson(input)
    }

    @TypeConverter
    fun listOfStringFromJson(input : String) : List<String> {
        return mGson.fromJson<List<String>>(input)
    }

    @TypeConverter
    fun mapOfStringToDoubleToJson(input : Map<String,Double>) : String {
        return mGson.toJson(input)
    }

    @TypeConverter
    fun mapOfStringToDoubleFromJson(input : String) : Map<String,Double>? {
        return mGson.fromJson<Map<String, Double>>(input)
    }

    @TypeConverter
    fun mapOfStringToIntegerToJson(input : Map<String, Int>) : String {
        return mGson.toJson(input)
    }

    @TypeConverter
    fun mapOfStringToIntegerFromJson(input : String) : Map<String, Int>? {
        return mGson.fromJson<Map<String, Int>>(input)
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
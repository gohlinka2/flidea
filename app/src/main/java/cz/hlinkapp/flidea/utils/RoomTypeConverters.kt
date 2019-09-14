package cz.hlinkapp.flidea.utils

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import cz.hlinkapp.flidea.model.BagLimit
import cz.hlinkapp.flidea.model.Country
import cz.hlinkapp.flidea.model.Route

class RoomTypeConverters {

    @TypeConverter
    fun listOfStringToJson(input : List<String>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun listOfStringFromJson(input : String) : List<String> {
        return GsonBuilder().serializeNulls().create().fromJson(input,List::class.java) as List<String>
    }

    @TypeConverter
    fun listOfRouteToJson(input : List<Route>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun listOfRouteFromJson(input : String) : List<Route> {
        return GsonBuilder().serializeNulls().create().fromJson(input,List::class.java) as List<Route>
    }

    @TypeConverter
    fun mapOfStringToDoubleToJson(input : Map<String,Double>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun mapOfStringToDoubleFromJson(input : String) : Map<String,Double>? {
        return GsonBuilder().serializeNulls().create().fromJson(input,Map::class.java) as? Map<String, Double>
    }

    @TypeConverter
    fun mapOfStringToIntegerToJson(input : Map<String, Int>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun mapOfStringToIntegerFromJson(input : String) : Map<String, Int>? {
        return GsonBuilder().serializeNulls().create().fromJson(input,Map::class.java) as? Map<String, Int>
    }

    @TypeConverter
    fun bagLimitToJson(input : BagLimit) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun bagLimitFromJson(input : String) : BagLimit {
        return GsonBuilder().serializeNulls().create().fromJson(input,BagLimit::class.java)
    }

    @TypeConverter
    fun countryToJson(input : Country) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun countryFromJson(input : String) : Country {
        return GsonBuilder().serializeNulls().create().fromJson(input,Country::class.java)
    }
}
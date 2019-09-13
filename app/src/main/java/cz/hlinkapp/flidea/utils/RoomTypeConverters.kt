package cz.hlinkapp.flidea.utils

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import cz.hlinkapp.flidea.model.BagLimit
import cz.hlinkapp.flidea.model.Country

class RoomTypeConverters {

    @TypeConverter
    fun listToJson(input : List<*>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun listFromJson(input : String) : List<*> {
        return GsonBuilder().serializeNulls().create().fromJson(input,List::class.java)
    }

    @TypeConverter
    fun mapToJson(input : Map<*,*>) : String {
        return GsonBuilder().serializeNulls().create().toJson(input)
    }

    @TypeConverter
    fun mapFromJson(input : String) : Map<*,*> {
        return GsonBuilder().serializeNulls().create().fromJson(input,Map::class.java)
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
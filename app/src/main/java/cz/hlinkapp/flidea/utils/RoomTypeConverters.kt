package cz.hlinkapp.flidea.utils

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder

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
}
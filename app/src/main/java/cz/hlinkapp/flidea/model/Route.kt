package cz.hlinkapp.flidea.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

/**
 * A model class representing a single flight part in a [Flight]/Flidea.
 * Eg. If the flight has one stopover, the [Flight] model would contain 4 instances of [Route]:
 * 0: Start airport -> stopover airport
 * 1: Stopover airport -> destination airport
 * 2: Dest. A. -> Stopover A.
 * 3: Stopover A. -> Start A.
 */
data class Route (

	@PrimaryKey
	@SerializedName("id") var id : String,
//	@SerializedName("combination_id") var combination_id : String,
	@SerializedName("return") var mReturn : Int,
//	@SerializedName("original_return") var original_return : Int,
//	@SerializedName("source") var source : String,
//	@SerializedName("found_on") var found_on : String,
//	@SerializedName("price") var price : Int,
	@SerializedName("aTime") var aTime : Long,
	@SerializedName("dTime") var dTime : Long,
	@SerializedName("aTimeUTC") var aTimeUTC : Long,
	@SerializedName("dTimeUTC") var dTimeUTC : Long,
	@SerializedName("mapIdfrom") var mapIdfrom : String,
	@SerializedName("mapIdto") var mapIdto : String,
	@SerializedName("cityTo") var cityTo : String,
	@SerializedName("cityFrom") var cityFrom : String,
	@SerializedName("flyTo") var flyTo : String,
	@SerializedName("airline") var airline : String,
	@SerializedName("operating_carrier") var operating_carrier : String,
	@SerializedName("equipment") var equipment : String,
	@SerializedName("flyFrom") var flyFrom : String,
	@SerializedName("latFrom") var latFrom : Double,
	@SerializedName("lngFrom") var lngFrom : Double,
	@SerializedName("latTo") var latTo : Double,
	@SerializedName("lngTo") var lngTo : Double,
	@SerializedName("flight_no") var flight_no : Int,
	@SerializedName("vehicle_type") var vehicle_type : String,
//	@SerializedName("refresh_timestamp") var refresh_timestamp : Int,
	@SerializedName("bags_recheck_required") var bags_recheck_required : Boolean,
//	@SerializedName("guarantee") var guarantee : Boolean,
	@SerializedName("fare_classes") var fare_classes : String,
	@SerializedName("fare_basis") var fare_basis : String,
	@SerializedName("fare_family") var fare_family : String
//	@SerializedName("fare_category") var fare_category : String,
//	@SerializedName("last_seen") var last_seen : Int,
//	@SerializedName("operating_flight_no") var operating_flight_no : Int
)
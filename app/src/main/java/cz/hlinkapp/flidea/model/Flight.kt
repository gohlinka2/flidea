package cz.hlinkapp.flidea.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


@Entity
data class Flight (
	@PrimaryKey
	@SerializedName("id") var id : String,
	var display_day_timestamp : Long,
	var fetched_timestamp: Long,
	@SerializedName("bags_price") var bags_price : Map<String,Int>,
	@SerializedName("baglimit") var baglimit : BagLimit,
//	@SerializedName("p1") var p1 : Int,
//	@SerializedName("p2") var p2 : Int,
//	@SerializedName("p3") var p3 : Int,
	@SerializedName("price") var price : Double,
	@SerializedName("route") var route : List<Route>,
//	@SerializedName("airlines") var airlines : List<String>,
//	@SerializedName("pnr_count") var pnr_count : Int,
//	@SerializedName("transfers") var transfers : List<String>,
//	@SerializedName("has_airport_change") var has_airport_change : Boolean,
//	@SerializedName("availability") var availability : Availability,
	@SerializedName("dTime") var dTime : Int,
	@SerializedName("dTimeUTC") var dTimeUTC : Int,
	@SerializedName("aTime") var aTime : Int,
	@SerializedName("aTimeUTC") var aTimeUTC : Int,
	@SerializedName("nightsInDest") var nightsInDest : Int,
	@SerializedName("flyFrom") var flyFrom : String,
	@SerializedName("flyTo") var flyTo : String,
	@SerializedName("cityFrom") var cityFrom : String,
	@SerializedName("cityTo") var cityTo : String,
	@SerializedName("countryFrom") var countryFrom : Country,
	@SerializedName("countryTo") var countryTo : Country,
	@SerializedName("mapIdfrom") var mapIdfrom : String,
	@SerializedName("mapIdto") var mapIdto : String,
	@SerializedName("distance") var distance : Double,
//	@SerializedName("routes") var routes : List<List<String>>,
	@SerializedName("virtual_interlining") var virtual_interlining : Boolean,
	@SerializedName("fly_duration") var fly_duration : String,
//	@SerializedName("duration") var duration : Duration,
	@SerializedName("return_duration") var return_duration : String,
//	@SerializedName("facilitated_booking_available") var facilitated_booking_available : Boolean,
	@SerializedName("type_flights") var type_flights : List<String>,
//	@SerializedName("found_on") var found_on : List<String>,
	@SerializedName("conversion") var conversion : Map<String,Double>,
	@SerializedName("booking_token") var booking_token : String,
	@SerializedName("popularity") var popularity : Long,
	@SerializedName("quality") var quality : Double,
	@SerializedName("deep_link") var deep_link : String
) {
	companion object {
		const val DISPLAY_DAY_TIMESTAMP_DEF_VAL = Long.MIN_VALUE
	}
}
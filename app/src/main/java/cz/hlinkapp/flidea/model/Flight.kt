
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
	@SerializedName("id") val id : String,
	@SerializedName("bags_price") val bags_price : Map<String,Int>,
	@SerializedName("baglimit") val baglimit : BagLimit,
//	@SerializedName("p1") val p1 : Int,
//	@SerializedName("p2") val p2 : Int,
//	@SerializedName("p3") val p3 : Int,
	@SerializedName("price") val price : Double,
	@SerializedName("route") val route : List<Route>,
//	@SerializedName("airlines") val airlines : List<String>,
//	@SerializedName("pnr_count") val pnr_count : Int,
//	@SerializedName("transfers") val transfers : List<String>,
//	@SerializedName("has_airport_change") val has_airport_change : Boolean,
//	@SerializedName("availability") val availability : Availability,
	@SerializedName("dTime") val dTime : Int,
	@SerializedName("dTimeUTC") val dTimeUTC : Int,
	@SerializedName("aTime") val aTime : Int,
	@SerializedName("aTimeUTC") val aTimeUTC : Int,
	@SerializedName("nightsInDest") val nightsInDest : Int,
	@SerializedName("flyFrom") val flyFrom : String,
	@SerializedName("flyTo") val flyTo : String,
	@SerializedName("cityFrom") val cityFrom : String,
	@SerializedName("cityTo") val cityTo : String,
	@SerializedName("countryFrom") val countryFrom : Country,
	@SerializedName("countryTo") val countryTo : Country,
	@SerializedName("mapIdfrom") val mapIdfrom : String,
	@SerializedName("mapIdto") val mapIdto : String,
	@SerializedName("distance") val distance : Double,
//	@SerializedName("routes") val routes : List<List<String>>,
	@SerializedName("virtual_interlining") val virtual_interlining : Boolean,
	@SerializedName("fly_duration") val fly_duration : String,
//	@SerializedName("duration") val duration : Duration,
	@SerializedName("return_duration") val return_duration : String,
//	@SerializedName("facilitated_booking_available") val facilitated_booking_available : Boolean,
	@SerializedName("type_flights") val type_flights : List<String>,
//	@SerializedName("found_on") val found_on : List<String>,
	@SerializedName("conversion") val conversion : Map<String,Double>,
	@SerializedName("booking_token") val booking_token : String,
//	@SerializedName("quality") val quality : Double,
	@SerializedName("deep_link") val deep_link : String
)
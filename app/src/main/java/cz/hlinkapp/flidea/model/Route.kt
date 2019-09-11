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
data class Route (

	@PrimaryKey
	@SerializedName("id") val id : String,
//	@SerializedName("combination_id") val combination_id : String,
	@SerializedName("return") val mReturn : Int,
//	@SerializedName("original_return") val original_return : Int,
//	@SerializedName("source") val source : String,
//	@SerializedName("found_on") val found_on : String,
//	@SerializedName("price") val price : Int,
	@SerializedName("aTime") val aTime : Int,
	@SerializedName("dTime") val dTime : Int,
	@SerializedName("aTimeUTC") val aTimeUTC : Int,
	@SerializedName("dTimeUTC") val dTimeUTC : Int,
	@SerializedName("mapIdfrom") val mapIdfrom : String,
	@SerializedName("mapIdto") val mapIdto : String,
	@SerializedName("cityTo") val cityTo : String,
	@SerializedName("cityFrom") val cityFrom : String,
	@SerializedName("flyTo") val flyTo : String,
	@SerializedName("airline") val airline : String,
	@SerializedName("operating_carrier") val operating_carrier : String,
	@SerializedName("equipment") val equipment : String,
	@SerializedName("flyFrom") val flyFrom : String,
	@SerializedName("latFrom") val latFrom : Double,
	@SerializedName("lngFrom") val lngFrom : Double,
	@SerializedName("latTo") val latTo : Double,
	@SerializedName("lngTo") val lngTo : Double,
	@SerializedName("flight_no") val flight_no : Int,
	@SerializedName("vehicle_type") val vehicle_type : String,
//	@SerializedName("refresh_timestamp") val refresh_timestamp : Int,
	@SerializedName("bags_recheck_required") val bags_recheck_required : Boolean,
//	@SerializedName("guarantee") val guarantee : Boolean,
	@SerializedName("fare_classes") val fare_classes : String,
	@SerializedName("fare_basis") val fare_basis : String,
	@SerializedName("fare_family") val fare_family : String
//	@SerializedName("fare_category") val fare_category : String,
//	@SerializedName("last_seen") val last_seen : Int,
//	@SerializedName("operating_flight_no") val operating_flight_no : Int
)
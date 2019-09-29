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


/**
 * A model class representing a flight/Flidea(flight idea).
 * Also the main Room entity.
 */
@Entity
data class Flight (

	var display_day_timestamp : Long,
	var fetched_timestamp: Long,
	var currency: String,

	@PrimaryKey
	@SerializedName("id") var id : String,
	@SerializedName("price") var price : Double,
	@SerializedName("route") var route : List<Route>,
	@SerializedName("flyTo") var flyTo : String,
	@SerializedName("cityTo") var cityTo : String,
	@SerializedName("countryTo") var countryTo : Country,
	@SerializedName("mapIdTo") var mapIdTo : String,
	@SerializedName("popularity") var popularity : Long,
	@SerializedName("quality") var quality : Double,
	@SerializedName("deep_link") var deep_link : String
) {
	companion object {
		/**
		 * Default value for the display day timestamp.
		 * The timestamp should be set to this value in all newly downloaded and undisplayed flights, before marking them for display.
		 */
		const val DISPLAY_DAY_TIMESTAMP_DEF_VAL = Long.MIN_VALUE
	}
}
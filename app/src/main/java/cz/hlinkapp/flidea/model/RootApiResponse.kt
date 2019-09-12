import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class RootApiResponse (

    @SerializedName("search_id") val search_id : String,
    @SerializedName("data") val data: List<Flight>,
//    @SerializedName("connections") val connections : List<String>,
//    @SerializedName("time") val time : Int,
    @SerializedName("currency") val currency : String,
    @SerializedName("currency_rate") val currency_rate : Int,
//    @SerializedName("fx_rate") val fx_rate : Int,
//    @SerializedName("refresh") val refresh : List<String>,
//    @SerializedName("del") val del : Double,
//    @SerializedName("ref_tasks") val ref_tasks : List<String>,
    @SerializedName("search_params") val search_params : SearchParams
//    @SerializedName("all_stopover_airports") val all_stopover_airports : List<String>,
//    @SerializedName("all_airlines") val all_airlines : List<String>,
//    @SerializedName("_results") val _results : Int
)
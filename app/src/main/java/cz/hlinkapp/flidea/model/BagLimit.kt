package cz.hlinkapp.flidea.model


import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class BagLimit (

	@SerializedName("hold_width") var hold_width : Int,
	@SerializedName("hold_height") var hold_height : Int,
	@SerializedName("hold_length") var hold_length : Int,
	@SerializedName("hold_dimensions_sum") var hold_dimensions_sum : Int,
	@SerializedName("hold_weight") var hold_weight : Int,
	@SerializedName("hand_width") var hand_width : Int,
	@SerializedName("hand_height") var hand_height : Int,
	@SerializedName("hand_length") var hand_length : Int,
	@SerializedName("hand_weight") var hand_weight : Int
)
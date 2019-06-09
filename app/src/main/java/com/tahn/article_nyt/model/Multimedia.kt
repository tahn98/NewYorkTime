package com.tahn.article_nyt.model

import com.google.gson.annotations.SerializedName

data class Multimedia (

	@SerializedName("rank") val rank : Int,
	@SerializedName("subtype") val subtype : String,
	@SerializedName("caption") val caption : String,
	@SerializedName("credit") val credit : String,
	@SerializedName("type") val type : String,
	@SerializedName("url") val url : String,
	@SerializedName("height") val height : Int,
	@SerializedName("width") val width : Int,
//	@SerializedName("legacy") val legacy : Legacy,
	@SerializedName("subType") val subType : String,
	@SerializedName("crop_name") val crop_name : String
)
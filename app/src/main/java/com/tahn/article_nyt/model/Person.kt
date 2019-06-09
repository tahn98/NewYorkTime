package com.tahn.article_nyt.model

import com.google.gson.annotations.SerializedName
data class Person (

	@SerializedName("firstname") val firstname : String,
	@SerializedName("middlename") val middlename : String,
	@SerializedName("lastname") val lastname : String,
	@SerializedName("qualifier") val qualifier : String,
	@SerializedName("title") val title : String,
	@SerializedName("role") val role : String,
	@SerializedName("organization") val organization : String,
	@SerializedName("rank") val rank : Int
)
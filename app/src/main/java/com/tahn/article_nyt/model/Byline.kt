package com.tahn.article_nyt.model
import com.google.gson.annotations.SerializedName
data class Byline (

	@SerializedName("original") val original : String,
	@SerializedName("person") val person : List<Person>,
	@SerializedName("organization") val organization : String
)
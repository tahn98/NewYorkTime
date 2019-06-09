package com.tahn.article_nyt.model

import com.google.gson.annotations.SerializedName
data class Headline (

	@SerializedName("main") val main : String,
	@SerializedName("kicker") val kicker : String,
	@SerializedName("content_kicker") val content_kicker : String,
	@SerializedName("print_headline") val print_headline : String,
	@SerializedName("name") val name : String,
	@SerializedName("seo") val seo : String,
	@SerializedName("sub") val sub : String
)
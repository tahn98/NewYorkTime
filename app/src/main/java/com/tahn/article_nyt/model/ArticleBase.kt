package com.tahn.article_nyt.model

import com.google.gson.annotations.SerializedName
data class ArticleBase (

	@SerializedName("status") val status : String,
	@SerializedName("copyright") val copyright : String,
	@SerializedName("response") val response : Response
)
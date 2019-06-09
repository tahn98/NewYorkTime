package com.tahn.article_nyt.model

import com.google.gson.annotations.SerializedName

data class Response (

	@SerializedName("docs") val docs : List<Docs>,
	@SerializedName("meta") val meta : Meta
)
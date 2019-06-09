package com.tahn.article_nyt.model
import com.google.gson.annotations.SerializedName
data class Docs (

	@SerializedName("web_url") val web_url : String,
	@SerializedName("snippet") val snippet : String,
	@SerializedName("lead_paragraph") val lead_paragraph : String,
	@SerializedName("abstract") val abstract : String,
	@SerializedName("print_page") val print_page : String,
	@SerializedName("source") val source : String,
	@SerializedName("multimedia") val multimedia : List<Multimedia>,
	@SerializedName("headline") val headline : Headline,
	@SerializedName("keywordswords") val keywordswords : List<String>,
	@SerializedName("pub_date") val pub_date : String,
	@SerializedName("document_type") val document_type : String,
	@SerializedName("news_desk") val news_desk : String,
	@SerializedName("section_name") val section_name : String,
	@SerializedName("byline") val byline : Byline,
	@SerializedName("type_of_material") val type_of_material : String,
	@SerializedName("_id") val _id : String,
	@SerializedName("word_count") val word_count : Int,
	@SerializedName("uri") val uri : String
)
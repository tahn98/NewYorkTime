package com.tahn.article_nyt.api

import com.tahn.article_nyt.model.ArticleBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    //urls
    @GET("articlesearch.json")
    fun query(@Query("begin_date") begin_date : String?,
              @Query("sort") sort : String?,
              @Query("facet_fields") field : String?,
              @Query("page") page : Int?,
              @Query("fq") fq : String?): Call<ArticleBase>
}
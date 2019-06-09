package com.tahn.article_nyt.feature.Main

import com.tahn.article_nyt.api.ClientRetrofit
import com.tahn.article_nyt.model.ArticleBase
import com.tahn.article_nyt.model.Docs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view : MainInterface.View) : MainInterface.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun getArticle(beginDate: String?, sort: String?, values: String?, page : Int?, filter : String?) {
        var call = ClientRetrofit.createService().query(beginDate, sort, values, page, filter)

        call.enqueue(object : Callback<ArticleBase> {
            override fun onFailure(call: Call<ArticleBase>, t: Throwable) {
                view.onFailure(t)
            }

            override fun onResponse(call: Call<ArticleBase>, response: Response<ArticleBase>) {
                view.onResponse(response.body()?.response?.docs as ArrayList<Docs?>)
            }
        })
    }
}
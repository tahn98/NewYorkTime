package com.tahn.article_nyt.feature.Main

import com.tahn.article_nyt.model.Docs

interface MainInterface{

    interface View{
        fun setPresenter(presenter: Presenter)

        fun onResponse(listArticle : ArrayList<Docs?>?)

        fun onFailure(t : Throwable)
    }

    interface Presenter{
        fun getArticle(beginDate : String?, sort : String?, values : String?, page : Int?, filter : String?)
    }
}
package com.tahn.article_nyt.feature.Main


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tahn.article_nyt.R
import com.tahn.article_nyt.adapter.ArticleAdapter
import com.tahn.article_nyt.feature.WebView.WebViewActivity
import com.tahn.article_nyt.model.Docs
import com.tahn.article_nyt.utils.EndlessRecyclerViewScrollListener
import com.tahn.article_nyt.utils.baseconst
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainInterface.View, FilterDialog.OnInputListener {

    private var listArticle : ArrayList<Docs?> = ArrayList()
    private var articleAdapter : ArticleAdapter? = null
    private var presenter : MainInterface.Presenter? = null
    private var scrollListener : EndlessRecyclerViewScrollListener? = null
    private var date : String? = null
    private var sort : String? = null
    private var values : String? = null
    private var nextPage : Int = 0
    private var query_filter : String? = null

    init {
        presenter = MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isNetWorkAvailable()){
            Snackbar.make(mainConstrainLayout, "Internet is available", Snackbar.LENGTH_LONG).show()
        }
        else{
            Snackbar.make(mainConstrainLayout, "Internet is not available", Snackbar.LENGTH_LONG).show()
        }

        progress_horizontal.visibility = View.INVISIBLE
        initRecycleView()
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onResume() {
        super.onResume()
    }

    private fun articleItemClicked(doc : Docs?){
        val detailIntent = Intent(this, WebViewActivity::class.java)
        detailIntent.putExtra(baseconst.DETAIL_KEY, doc?.web_url)
        startActivity(detailIntent)
    }

    private fun initRecycleView(){
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mainRecycleView.layoutManager = staggeredGridLayoutManager

        articleAdapter = ArticleAdapter(applicationContext, listArticle, { docs: Docs? -> articleItemClicked(docs)})
        mainRecycleView.adapter = articleAdapter

        articleAdapter!!.notifyDataSetChanged()
        scrollListener = object : EndlessRecyclerViewScrollListener(staggeredGridLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Handler().postDelayed({
                    progress_horizontal.visibility = View.VISIBLE
                    nextPage++
                    presenter!!.getArticle(date, sort, values, nextPage, query_filter)
                },2500)
            }
        }

        mainRecycleView.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    private fun clearArticleAdapter(){
        this.listArticle.clear()
        articleAdapter!!.notifyDataSetChanged()
    }

    fun isNetWorkAvailable() : Boolean{
        var connectivityManager : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo : NetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                clearArticleAdapter()
                query_filter = query
                presenter!!.getArticle(date, sort, values, 0, query_filter)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_filter ->{
                val fragment = FilterDialog()
                fragment.show(supportFragmentManager, "filter_dialog")
            }
            R.id.menu_clear ->{
                clearArticleAdapter()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun sendInput(date: String?, sort: String?, values: String?) {
        this.date = date
        this.sort = sort
        this.values = values
    }

    override fun setPresenter(presenter: MainInterface.Presenter) {
        this.presenter = presenter
    }

    override fun onResponse(listArticle: ArrayList<Docs?>?) {
        if (listArticle != null) {
            progress_horizontal.visibility = View.INVISIBLE
            this.listArticle.addAll(listArticle)
            articleAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onFailure(t: Throwable) {
        Log.d("Failure", t.toString())
    }
}

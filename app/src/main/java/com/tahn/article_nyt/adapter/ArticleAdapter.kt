package com.tahn.article_nyt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tahn.article_nyt.R
import com.tahn.article_nyt.model.Docs


class ArticleAdapter(private val context : Context?, private val listArticle : ArrayList<Docs?>  ,private val clickListener : (Docs?) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val widget_image = 1
    val widget_noimage = 0

    override fun getItemViewType(position: Int): Int {
        return if (listArticle[position]?.let { getImageURL(it) } == ""){
            widget_noimage
        }else{
            widget_image
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            listArticle?.get(position)?.let { holder.bind(it, clickListener) }
        }
        else if (holder is DataTextViewHolder){
            listArticle?.get(position)?.let { holder.bind(it, clickListener) }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
//        val root = LayoutInflater.from(p0.context).inflate(R.layout.article_widget, p0, false)
//        val root : View? = null
//
//        return DataViewHolder(root)
        val root : View
        return if (p1 == widget_image){
            root = LayoutInflater.from(p0.context).inflate(R.layout.article_widget, p0, false)
            DataViewHolder(root)
        } else{
            root = LayoutInflater.from(p0.context).inflate(R.layout.article_widget_noimage, p0, false)
            DataTextViewHolder(root)
        }
    }

    override fun getItemCount(): Int {
        return listArticle?.size!!
    }

    inner class DataTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val headline = itemView.findViewById<TextView>(R.id.wg_text_no_image)
        private val parahead = itemView.findViewById<TextView>(R.id.lead_paragraph)

        fun bind(docs: Docs, clickListener: (Docs?) -> Unit){
            headline.text = docs.headline.main
            parahead.text = docs.lead_paragraph
            itemView.setOnClickListener { clickListener(docs) }
        }
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headline = itemView.findViewById<TextView>(R.id.wg_text)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.wg_img)

        fun bind(docs: Docs, clickListener: (Docs?) -> Unit) {
            headline.text = docs.headline.main
            Glide.with(context?.applicationContext ?: return)
                .load("https://www.nytimes.com/" + "${getImageURL(docs)}")
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .error(R.drawable.ic_launcher_background)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(thumbnail)
            itemView.setOnClickListener { clickListener(docs) }
        }
    }

    fun getImageURL(doc: Docs): String {
        var size: Int = doc.multimedia.size
        if (size == 0) {
            return ""
        }
        return doc.multimedia[0].url
    }
}
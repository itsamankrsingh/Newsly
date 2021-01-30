package com.magician.newsly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(
    val context: Context,
    val listener: NewsItemClicked
) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val articles: ArrayList<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        val viewHolder = ArticleViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(articles[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateNews(updateArticles: ArrayList<Article>) {
        articles.clear()
        articles.addAll(updateArticles)
        notifyDataSetChanged()
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsTitle = itemView.findViewById<TextView>(R.id.news_title_text_view)
        var newsDescription = itemView.findViewById<TextView>(R.id.news_description_text_view)
        var newsImage = itemView.findViewById<ImageView>(R.id.news_image_view)
    }
}

interface NewsItemClicked {
    fun onItemClicked(article: Article)
}
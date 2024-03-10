package com.comrades.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ProfileAdapter(
    private val articles: List<Article>
) : RecyclerView.Adapter<ProfileAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionTextView: TextView = itemView.findViewById(R.id.item_description)
        private val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        private val articleImageView: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(article: Article) {
            descriptionTextView.text = article.text
            titleTextView.text = article.title
            articleImageView.setImageResource(R.drawable.example_article_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item_view, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size
}
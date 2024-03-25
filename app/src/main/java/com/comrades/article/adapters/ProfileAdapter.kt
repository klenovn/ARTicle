package com.comrades.article.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.comrades.article.R
import com.comrades.article.activities.ArticleActivity
import com.comrades.article.models.ArticleResponse

class ProfileAdapter(
    private var articles: List<ArticleResponse> = ArrayList()
) : RecyclerView.Adapter<ProfileAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionTextView: TextView = itemView.findViewById(R.id.item_description)
        private val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        private val articleImageView: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(article: ArticleResponse) {
            descriptionTextView.text = article.caption
            titleTextView.text = article.title
            articleImageView.setImageResource(article.imageId)

            itemView.setOnClickListener{
//                (it.context as? AppCompatActivity)?.apply {
//                    supportFragmentManager.beginTransaction().add(R.id.fragment_container_view,
//                        ArticleActivity)
//                }
                val articleIntent = Intent(it.context, ArticleActivity::class.java).apply {
                    putExtra("id", titleTextView.text.toString())
                }
                startActivity(it.context, articleIntent, null)
//                Toast.makeText(it.context, "pomogite", Toast.LENGTH_SHORT).show()
            }
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

    fun updateArticles(articles: List<ArticleResponse>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun getItemCount() = articles.size
}
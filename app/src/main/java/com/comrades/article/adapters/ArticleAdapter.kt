package com.comrades.article.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.comrades.article.R
import com.comrades.article.activities.ArticleActivity
import com.comrades.article.activities.ProfileActivity
import com.comrades.article.models.ArticleResponse

class ArticleAdapter(
    private val articles: List<ArticleResponse>
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.swipeable_article_piece, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class ArticleViewHolder(private val articleView: View) :
        RecyclerView.ViewHolder(articleView) {
        private val authorNickname: TextView =
            articleView.findViewById(R.id.swipeable_author_nickname)
        private val title: TextView =
            articleView.findViewById(R.id.swipeable_title_tv)
        private val caption: TextView =
            articleView.findViewById(R.id.swipeable_caption_tv)
        private val description: TextView =
            articleView.findViewById(R.id.swipeable_description_tv)
        private val image: ImageView =
            articleView.findViewById(R.id.swipeable_image_iv)

        private val moreButton: Button =
            articleView.findViewById(R.id.more_button)

        fun bind(article: ArticleResponse) {
            title.text = article.title
            authorNickname.text = "@${article.authorNickname}"
            caption.text = article.caption
            description.text = article.description
            image.setImageResource(article.imageId)
            moreButton.setOnClickListener {
//                val action = MainMenuSwipeFragmentDirections
//                    .actionMainMenuSwipeFragmentToCurrentArticleFragment(article.id)
//                articleView.findNavController()
//                    .navigate(action)
                val articleIntent = Intent(it.context, ArticleActivity::class.java).apply {
                    putExtra("id", article.id)
                }
                ContextCompat.startActivity(it.context, articleIntent, null)
            }

            authorNickname.setOnClickListener {
                val profileIntent = Intent(it.context, ProfileActivity::class.java).apply {
                    putExtra("nickname", authorNickname.text.toString()) }
                ContextCompat.startActivity(it.context, profileIntent, null)
            }
        }

    }

}
package com.comrades.article.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.comrades.article.R
import com.comrades.article.controllers.Controller
import com.comrades.article.models.ArticleResponse

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)
        val currId : Int = intent.getIntExtra(ID, 0)
        val current: ArticleResponse = Controller.getDataById(currId)

        // AuthorTV
        val authorTV : TextView = findViewById(R.id.article_author_tv)
        "@${current.authorNickname}".also { authorTV.text = it }

        // TitleTV
        val titleTV : TextView = findViewById(R.id.article_title_tv)
        titleTV.text = current.title

        // DescriptionTV
        findViewById<TextView>(R.id.article_description_tv)
            .apply{ text = current.description}

        // CaptionTV
        findViewById<TextView>(R.id.article_caption_tv)
            .apply { text = current.caption }

        // ContentsTV
        findViewById<TextView>(R.id.article_contents_tv)
            .apply { text = current.contents }

        // ImageView
        findViewById<ImageView>(R.id.article_image_iv)
            .apply { setImageResource(current.imageId) }

        val closeButton: ImageButton = findViewById(R.id.back_button)

        val shareButton: Button = findViewById(R.id.button_share)
        shareButton.setOnClickListener {
            val sendIntent : Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, current.title)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        authorTV.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java).apply {
                putExtra(ProfileActivity.NICKNAME, authorTV.text.toString())
            })
        }

        closeButton.setOnClickListener{
            finish()
        }
    }

    companion object {
        const val ID = "id"
    }
}
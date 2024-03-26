package com.comrades.article.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.d("MY_TAG", intent.getIntExtra(ID, 1).toString())
        val currId : Int = intent.getIntExtra(ID, 1)
        val current: ArticleResponse = Controller.getDataById(currId)

        val authorTV : TextView = findViewById(R.id.author_tv__second_fragment)
        authorTV.text = "@${current.authorNickname}"
        val titleTV : TextView = findViewById(R.id.title_tv__second_fragment)
        titleTV.text = current.title
        val descTV : TextView = findViewById(R.id.description_tv__second_fragment)
        descTV.text = current.description
        val captionTV : TextView = findViewById(R.id.caption_tv__second_fragment)
        captionTV.text = current.caption
        val contentsTV : TextView = findViewById(R.id.contents_tv__second_fragment)
        contentsTV.text = current.contents
        val imageView: ImageView = findViewById(R.id.avatar)
        imageView.setImageResource(current.imageId)
        val closeButtonTV: ImageButton = findViewById(R.id.back_button)

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
                putExtra("nickname", authorTV.text.toString())
            })
        }

        closeButtonTV.setOnClickListener{
            finish()
        }
    }

    companion object {
        const val ID = "id"
    }
}
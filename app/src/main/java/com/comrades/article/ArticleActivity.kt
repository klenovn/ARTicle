package com.comrades.article

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)
        Log.d("MY_TAG", intent.getIntExtra(ID, 1).toString())
        val currId = intent.getIntExtra(ID, 1)
        val current: ArticleResponse = Controller.getDataById(currId)

        val titleTV : TextView = findViewById(R.id.title_tv__second_fragment)
        titleTV.text = current.title
        val descTV : TextView = findViewById(R.id.description_tv__second_fragment)
        descTV.text = current.description
        val captionTV : TextView = findViewById(R.id.caption_tv__second_fragment)
        captionTV.text = current.caption
        val contentsTV : TextView = findViewById(R.id.contents_tv__second_fragment)
        contentsTV.text = current.contents
        val imageView: ImageView = findViewById(R.id.avatar)
        imageView.setImageURI(current.imageUri)


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

    }

    companion object {
        const val ID = "id"
    }
}
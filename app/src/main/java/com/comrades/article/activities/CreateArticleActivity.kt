package com.comrades.article.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.comrades.article.R
import com.comrades.article.controllers.Controller
import com.comrades.article.utilities.ArticleValidator
import com.google.android.material.button.MaterialButton

class CreateArticleActivity : AppCompatActivity() {
    private lateinit var createArticleImageView: ImageView
    private lateinit var pickImageButton: ImageButton
    private lateinit var closeButton: ImageButton
    private lateinit var postButton: MaterialButton
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var captionEditText: EditText
    private lateinit var descriptionEditText: EditText
    private var pickedImageUri: Uri? = null

    private val imageContract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        createArticleImageView.setImageURI(it)
        pickedImageUri = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_article)

        createArticleImageView = findViewById(R.id.create_article__content_image_view)
        pickImageButton = findViewById(R.id.create_article__add_photo)
        closeButton = findViewById(R.id.create_article__close)
        postButton = findViewById(R.id.create_article__post_button)
        titleEditText = findViewById(R.id.create_article__article_title)
        contentEditText = findViewById(R.id.create_article__article_content)
        captionEditText = findViewById(R.id.create_article__article_caption)
        descriptionEditText = findViewById(R.id.create_article__article_description)


        if (savedInstanceState != null) {
            pickedImageUri = savedInstanceState.getParcelable("imageUri")
            createArticleImageView.setImageURI(pickedImageUri)
        }

        pickImageButton.setOnClickListener {
            imageContract.launch("image/*")
        }

        closeButton.setOnClickListener {
            finish()
        }

        postButton.setOnClickListener {
            if (ArticleValidator.validateArticle(titleEditText, captionEditText, descriptionEditText, contentEditText)) {
                val newArticleId = Controller.createArticle("DebugUser", titleEditText.text.toString(), captionEditText.text.toString(), descriptionEditText.text.toString(), contentEditText.text.toString(),
                    R.drawable.jojo_main
                )
                val intent = Intent(this, ArticleActivity::class.java).apply {
                    putExtra("id", newArticleId)
                }
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("imageUri", pickedImageUri)
    }

}
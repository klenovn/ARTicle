package com.comrades.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class CreateArticleActivity : AppCompatActivity() {
    private lateinit var createArticleImageView: ImageView
    private lateinit var pickImageButton: ImageButton
    private lateinit var closeButton: ImageButton
    private lateinit var postButton: MaterialButton
    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private var pickedImageUri: Uri? = null

    private val imageContract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        createArticleImageView.setImageURI(it)
        pickedImageUri = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_article)

        createArticleImageView = findViewById(R.id.create_article_content_image_view)
        pickImageButton = findViewById(R.id.create_article_add_photo)
        closeButton = findViewById(R.id.create_article_close)
        postButton = findViewById(R.id.create_article_post_button)
        titleEditText = findViewById(R.id.article_heading)
        contentEditText = findViewById(R.id.article_content)

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
            if (validateInputs()) {
                val newArticleId = Controller.createArticle("DebugUser", titleEditText.text.toString(), "Caption", "Desc", contentEditText.text.toString(),  R.drawable.jojo_main)
                val intent = Intent(this, ArticleActivity::class.java).apply {
                    putExtra("id", newArticleId)
                }
                finish()
                startActivity(intent)
            }
        }
    }

    private fun validateInputs() : Boolean {
        return titleEditText.text.toString() != "" && contentEditText.text.toString() != ""
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("imageUri", pickedImageUri)
    }
}
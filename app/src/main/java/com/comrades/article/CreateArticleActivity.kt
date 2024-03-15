package com.comrades.article

import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class CreateArticleActivity : AppCompatActivity() {
    private lateinit var createArticleImageView: ImageView
    private lateinit var pickImageButton: ImageButton
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

        if (savedInstanceState != null) {
            pickedImageUri = savedInstanceState.getParcelable("imageUri")
            createArticleImageView.setImageURI(pickedImageUri)
        }

        pickImageButton.setOnClickListener {
            imageContract.launch("image/*")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("imageUri", pickedImageUri)
    }
}
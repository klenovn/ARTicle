package com.comrades.article

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class NavbarFragment : Fragment(R.layout.navbar_fragment) {

    private lateinit var createArticleButton: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createArticleButton = view.findViewById(R.id.create_article_button)

        createArticleButton.setOnClickListener {
            val intent = Intent(activity, CreateArticleActivity::class.java)
            startActivity(intent)
        }
    }

}
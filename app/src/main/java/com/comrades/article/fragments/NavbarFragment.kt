package com.comrades.article.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.comrades.article.R
import com.comrades.article.activities.CreateArticleActivity
import com.comrades.article.activities.MainMenuActivity

class NavbarFragment : Fragment(R.layout.navbar_fragment) {

    private lateinit var createArticleButton: ImageView
    private lateinit var swipeArticleButton: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createArticleButton = view.findViewById(R.id.create_article_button)
        swipeArticleButton = view.findViewById(R.id.swipe)

        createArticleButton.setOnClickListener {
            val intent = Intent(activity, CreateArticleActivity::class.java)
            startActivity(intent)
        }

        swipeArticleButton.setOnClickListener{
            val intent = Intent(activity, MainMenuActivity::class.java)
            activity?.finish()
            startActivity(intent)
        }



    }
}
package com.comrades.article

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileActivity: AppCompatActivity(R.layout.user_profile) {

    private lateinit var user: User
    private val profileArticlesViewModel = ProfileArticlesViewModel()
    private val profileAdapter = ProfileAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userNickname = intent.extras?.run {
            getString("nickname")?.substring(1)
        }

        if (userNickname == null) {
            finish()
            return
        }

        user = UserController.getUserByNickname(userNickname)

        findViewById<RecyclerView>(R.id.articles_rv).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter
        }

        findViewById<ImageView>(R.id.profile_image).apply {
            setImageResource(user.avatar)
        }
        findViewById<TextView>(R.id.profile_nickname).apply {
            text = "@${user.nickname}"
        }
        findViewById<TextView>(R.id.profile_description).apply {
            text = user.description
        }

        profileArticlesViewModel.updateArticles(userNickname)

        profileArticlesViewModel.getArticlesList().observe(this) {
            it?.let {
                profileAdapter.updateArticles(it)
            }
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}
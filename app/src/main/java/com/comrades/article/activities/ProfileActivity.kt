package com.comrades.article.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comrades.article.R
import com.comrades.article.adapters.ProfileAdapter
import com.comrades.article.controllers.UserController
import com.comrades.article.fragments.ProfileAvatarFragment
import com.comrades.article.models.User
import com.comrades.article.viewmodels.ProfileArticlesViewModel

class ProfileActivity: AppCompatActivity(R.layout.user_profile) {

    private lateinit var user: User
    private val profileArticlesViewModel = ProfileArticlesViewModel()
    private val profileAdapter = ProfileAdapter()
    private var avatarFragment: ProfileAvatarFragment = ProfileAvatarFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userNickname = intent.extras?.run {
            getString(NICKNAME)?.substring(1)
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
            "@${user.nickname}".also { text = it }
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

        findViewById<ImageView>(R.id.profile_image).setOnClickListener{
            val args = Bundle()
            args.putInt(AVATAR_ID, user.avatar)
            avatarFragment.arguments = args
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container_view,
                avatarFragment)
                .commit()
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }

    companion object {
        const val AVATAR_ID = "avatarId"
        const val NICKNAME = "nickname"
    }
}
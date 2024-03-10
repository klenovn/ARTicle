package com.comrades.article

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileActivity: AppCompatActivity(R.layout.user_profile) {

    private var user: User = User("Gojo_Satoru",
        "example_profile_image", "gay")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<RecyclerView>(R.id.search_rv).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = ProfileAdapter(fillList())
        }

        findViewById<ImageView>(R.id.profile_image).apply {
            setImageResource(R.drawable.example_profile_image)
        }
        findViewById<TextView>(R.id.profile_nickname).apply {
            text = "@" + user.nickname
        }
        findViewById<TextView>(R.id.profile_description).apply {
            text = user.description
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }


    private fun fillList(): List<Article> {
        val data = mutableListOf<Article>()
        (1..30).forEach { i ->
            data.add(Article("тайлер дырдын ${i}", "я больной х${i}"))
        }
        return data
    }
}
package com.comrades.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2

class MainMenuActivity : AppCompatActivity() {
    private lateinit var adapter: ArticleAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NavbarFragment>(R.id.navbar, "navbar")
            }
        }

        val progress = findViewById<FrameLayout>(R.id.progress_bar)


        val myCallback =  { result: List<ArticleResponse> ->
            progress.isVisible = false
            adapter = ArticleAdapter(this, result)
            viewPager = findViewById(R.id.pager)
            viewPager.adapter = adapter
        }

        Controller.loadData(myCallback)
    }

}
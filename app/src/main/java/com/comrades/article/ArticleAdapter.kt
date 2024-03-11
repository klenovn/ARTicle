package com.comrades.article

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    fragment: MainActivity,
    private val articles: List<ArticleResponse>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = articles.size

    override fun createFragment(position: Int): Fragment {
        val current = articles[position]

        return MainFragment.newInstance(current)
    }

}
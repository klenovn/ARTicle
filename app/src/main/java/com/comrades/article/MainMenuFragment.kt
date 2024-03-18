package com.comrades.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment


const val ID = "id"
const val AUTHOR = "authorNickname"
const val TITLE = "title"
const val DESCRIPTION = "description"
const val CAPTION = "caption"
const val IMAGE_ID = "image_id"


class MainFragment: Fragment(R.layout.main_menu_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            val authorTV : TextView = view.findViewById(R.id.author_tv__main_fragment)
            authorTV.text = "@${getString(AUTHOR)}"
            val titleTV: TextView = view.findViewById(R.id.title_tv__main_fragment)
            titleTV.text = getString(TITLE)
            val descTV: TextView = view.findViewById(R.id.description_tv__main_fragment)
            descTV.text = getString(DESCRIPTION)
            val captionTV: TextView = view.findViewById(R.id.caption_tv__main_fragment)
            captionTV.text = getString(CAPTION)
            val imageView: ImageView = view.findViewById(R.id.avatar)
            imageView.setImageResource(getInt(IMAGE_ID))

            authorTV.setOnClickListener {
                val profileIntent = Intent(it.context, ProfileActivity::class.java)
                profileIntent.putExtra("nickname", authorTV.text.toString())
                startActivity(profileIntent)
            }

        }
        val moreButton = view.findViewById<Button>(R.id.button_more)
        moreButton.setOnClickListener {
            val intent = Intent(it.context, ArticleActivity::class.java)
            intent.putExtra(ID, arguments?.getInt(ID))
            context?.startActivity(intent)
        }
    }

    companion object {
        fun newInstance(article: ArticleResponse) = MainFragment().apply {
            arguments = bundleOf(
                ID to article.id,
                AUTHOR to article.authorNickname,
                TITLE to article.title,
                DESCRIPTION to article.description,
                CAPTION to article.caption,
                IMAGE_ID to article.imageId
            )
        }
    }

}
package com.comrades.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_article, container, false)

        val currId = 0
        val current: ArticleResponse = Controller.getDataById(currId)

        // TitleTV
        view.findViewById<TextView>(R.id.title_tv)
            .apply { text = current.title }

        // DescriptionTV
        view.findViewById<TextView>(R.id.description_tv)
            .apply { text = current.description }

        //CaptionTV
        view.findViewById<TextView>(R.id.caption_tv)
            .apply { text = current.caption }

        // ContentsTV
        view.findViewById<TextView>(R.id.contents_tv)
            .apply { text = current.contents }

        // ImageView
        view.findViewById<ImageView>(R.id.article_image_iv)
            .apply { setImageResource(current.imageId) }

        val shareButton: Button = view.findViewById(R.id.button_share)
        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, current.title)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return view
    }
}
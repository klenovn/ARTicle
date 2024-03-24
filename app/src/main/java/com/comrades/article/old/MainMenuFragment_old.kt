package com.comrades.article.old

import androidx.fragment.app.Fragment


const val ID = "id"
const val TITLE = "title"
const val DESCRIPTION = "description"
const val CAPTION = "caption"
const val IMAGE_ID = "image_id"


class MainMenuFragment: Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.main_menu_fragment_old, container, false)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        arguments?.apply {
//            val titleTV: TextView = view.findViewById(R.id.title_tv__main_fragment)
//            titleTV.text = getString(TITLE)
//            val descTV: TextView = view.findViewById(R.id.description_tv__main_fragment)
//            descTV.text = getString(DESCRIPTION)
//            val captionTV: TextView = view.findViewById(R.id.caption_tv__main_fragment)
//            captionTV.text = getString(CAPTION)
//            val imageView: ImageView = view.findViewById(R.id.avatar)
//            imageView.setImageResource(getInt(IMAGE_ID))
//        }
//        val moreButton = view.findViewById<Button>(R.id.button_more)
//        moreButton.setOnClickListener {
//            val intent = Intent(it.context, ArticleActivity_old::class.java)
//            intent.putExtra(ID, arguments?.getInt(ID))
//            context?.startActivity(intent)
//        }
//    }
//
//    companion object {
//        fun newInstance(article: ArticleResponse) = MainMenuFragment().apply {
//            arguments = bundleOf(
//                ID to article.id,
//                TITLE to article.title,
//                DESCRIPTION to article.description,
//                CAPTION to article.caption,
//                IMAGE_ID to article.imageId
//            )
//        }
//    }

}
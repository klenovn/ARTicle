package com.comrades.article.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.comrades.article.R
import com.comrades.article.adapters.ArticleAdapter
import com.comrades.article.controllers.Controller
import com.comrades.article.models.ArticleResponse
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

class MainMenuSwipeFragment : Fragment() {

    private lateinit var adapter: ArticleAdapter
    private lateinit var swipeView: CardStackView
    private lateinit var manager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_swipe, container, false)

        swipeView = view.findViewById(R.id.fragment_swipe_view)

        val progressBar = view.findViewById<FrameLayout>(R.id.progress_bar)


        val myCallback = { result: List<ArticleResponse> ->
            progressBar.isVisible = false
            adapter = ArticleAdapter(result)
            swipeView.adapter = adapter
            swipeView.itemAnimator = DefaultItemAnimator()
            initManager()
            swipeView.layoutManager = manager
        }

        Controller.loadData(myCallback)

        return view
    }

    private fun initManager() {
        manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if (direction == Direction.Left)
                    Toast.makeText(context, "Disliked!!!", Toast.LENGTH_SHORT).show()
                else if (direction == Direction.Right)
                    Toast.makeText(context, "Liked!!!", Toast.LENGTH_SHORT).show()
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }
        })

        manager.setVisibleCount(VISIBLE_COUNT)
        manager.setTranslationInterval(TRANSLATION_INTERVAL)
        manager.setScaleInterval(SCALE_INTERVAL)
        manager.setMaxDegree(MAX_DEGREE)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setSwipeThreshold(SWIPE_THRESHOLD)
        manager.setStackFrom(StackFrom.Top)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
    }

    companion object {
        const val VISIBLE_COUNT = 3
        const val TRANSLATION_INTERVAL = 0.6f
        const val SCALE_INTERVAL = 0.8f
        const val MAX_DEGREE = 20.0f
        const val SWIPE_THRESHOLD = 0.3f
    }

}

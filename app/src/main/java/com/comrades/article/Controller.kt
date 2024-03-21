package com.comrades.article

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Controller {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val articlePool = HashMap<Int, ArticleResponse>()

    init {
        for (i in 0 .. 9) {
            val curr = ArticleResponse(
                i,
                "ART $i",
                "DESC $i",
                "CAP $i",
                "CONT $i",
                if (i % 2 == 0) R.drawable.jojo_main else R.drawable.jujutsu_main
            )
            articlePool[i] = curr
        }
    }

    fun getDataById(id: Int): ArticleResponse {
        return articlePool[id] ?: throw IllegalArgumentException("Exception")
    }

    fun loadData(callback: (result: List<ArticleResponse>) -> Unit) {
        scope.launch {
            delay(2000L)
            withContext(Dispatchers.Main) {
                callback(articlePool.values.toList())
            }

        }
    }

    fun createArticle(
        title: String,
        caption: String,
        description: String,
        contents: String,
        imageId: Int
    ) : Int {
        val countArticles = articlePool.size
        val article = ArticleResponse(countArticles, title, description, caption, contents, imageId)
        articlePool[countArticles] = article

        return countArticles
    }

}
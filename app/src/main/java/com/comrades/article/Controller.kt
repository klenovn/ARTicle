package com.comrades.article

import android.net.Uri
import android.provider.MediaStore.Audio.Media
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
                null
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
        imageUri: Uri?
    ) : Int {
        val countArticles = articlePool.size
        val article = ArticleResponse(countArticles, title, description, caption, contents, imageUri)
        articlePool[countArticles] = article

        return countArticles
    }

}
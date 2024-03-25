package com.comrades.article.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.comrades.article.controllers.Controller
import com.comrades.article.models.ArticleResponse

class ProfileArticlesViewModel () : ViewModel() {

    private var articlesList:
            MutableLiveData<List<ArticleResponse>> = MutableLiveData()

    fun getArticlesList() = articlesList

    fun updateArticles(nickname: String) {
        Controller.loadData{ it ->
            articlesList.value = it.filter { it.authorNickname == nickname }
        }
    }
}
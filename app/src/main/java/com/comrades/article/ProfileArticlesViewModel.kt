package com.comrades.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
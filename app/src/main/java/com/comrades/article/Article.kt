package com.comrades.article

import android.net.Uri

data class Article (
    val id: Int,
    var authorNickname: String = "",
    var title: String = "",
    val description: String,
    val caption: String,
    val contents: String,
    var image: Uri? = null
)
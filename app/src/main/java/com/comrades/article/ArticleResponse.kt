package com.comrades.article

import android.net.Uri

data class ArticleResponse(
    val id: Int,
    val title: String,
    val description: String,
    val caption: String,
    val contents: String,
    val imageUri: Uri?,
)
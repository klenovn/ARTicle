package com.comrades.article

import android.net.Uri

data class Article (
    var title: String = "",
    var text: String = "",
    var authorNickname: String = "",
    var image: Uri? = null
)

package com.comrades.article

data class User (
    var nickname: String = "",
    val email: String = "",
    val password: String = "",
    var avatar: Int = R.drawable.no_avatar,
    var description: String = ""
)

package com.comrades.article

data class User (
    var nickname: String = "",
    var avatar: String = "",
    var description: String = "",
    val email: String = "",
    val password: String = ""
)
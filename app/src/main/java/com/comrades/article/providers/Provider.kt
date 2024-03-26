package com.comrades.article.providers

import com.comrades.article.R
import com.comrades.article.models.User

object Provider {
    val database = mutableListOf<User>(
        User(
            "User1", "89109752525", "1234567", R.drawable.no_avatar,"string1",
        ),
        User(
            "User2", "89109752524", "18978927", R.drawable.no_avatar,"string2",
        ),
        User(
            "User3", "ddd@yahoo.com", "89765341567", R.drawable.no_avatar,"string3",
        )
    )

}
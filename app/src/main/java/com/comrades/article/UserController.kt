package com.comrades.article

object UserController {

        private val userPool = mutableListOf<User>()

        init {
            for (i in 0 .. 9) {
                val curr = User(
                    "author$i",
                    if (i % 2 == 0) R.drawable.jujutsu_main else R.drawable.jojo_main,
                    "my description $i",
                )
                userPool.add(curr)
            }
        }

        fun getUserByNickname(nickname: String) = (userPool.find { it.nickname == nickname }) ?:
        User("not_founded_user", R.drawable.no_avatar,"")
}

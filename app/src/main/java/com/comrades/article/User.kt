package com.comrades.article

data class User (
    var nickname: String = "",
    var avatar: String = "",
    var description: String = ""
)

object UserData{

    fun getUsers()= listOf(
        User("Gojo_Satoru", "example_profile_image","strongest"),
        User("author0", "example_profile_image", "my description"),
        User("user2203", "example_profile_image", "about me"),
        User("djfjfh", "example_profile_image", "я русский")
    )

    fun getUserByNickname(nickname: String) = (getUsers().find { it.nickname == nickname }) ?:
    User("Gojo_Satoru", "jojo_main","strongest")
}
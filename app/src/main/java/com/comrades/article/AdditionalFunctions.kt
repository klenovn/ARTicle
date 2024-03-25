package com.comrades.article

fun isValidField(fieldParam: String, fieldParam2: String = "", fieldPosition: Int): Boolean {

    val regExpPhone = Regex("^((\\+?7)|8)[0-9]{10}\$")
    val regExpEmail = Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+\$")
    val regPassword = Regex("^[a-zA-Z0-9._-]{7,20}\$")
    val regExpUsername = Regex("^[a-zA-Z0-9-]+\$")

    var flag = false
    when (fieldPosition) {
        1 -> flag = regExpPhone.matches(fieldParam) || regExpEmail.matches(fieldParam)
        2 -> flag = regExpUsername.matches(fieldParam)
        3 -> flag = regPassword.matches(fieldParam)
        4 -> flag = fieldParam == fieldParam2
    }
    return flag
}

package com.comrades.article

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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

fun exists(email: String, username: String, callback: (result: Pair<Boolean, Boolean>) -> Unit) {
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        delay(1000L)
        var result = true to true

        for (user in database) {
            if (user.email == email) {
                result = false to false
                break
            } else if (user.nickname == username) {
                result = true to false
                break
            }
        }

        withContext(Dispatchers.Main) {
            callback(result)
        }
    }
}

fun add(
    email: String,
    username: String,
    password: String,
    callback: (result: Boolean, error: Throwable?) -> Unit
) {
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        delay(2000L)
        try {
            database.add(User(nickname = username, email = email, password = password))

            var rand = (0..10).random()
            if (rand % 4 == 0)
                throw Exception("DatabaseException")
            withContext(Dispatchers.Main) {
                callback(true, null)
            }
        } catch (e: Throwable) {
            withContext(Dispatchers.Main) {
                callback(false, e)
            }
        }

    }
}



val database = mutableListOf<User>(
    User(
        "User1", "1", "string1", "89109752525", "1234567"
    ),
    User(
        "User2", "2", "string2", "89109752524", "18978927"
    ),
    User(
        "User3", "3", "string3", "ddd@yahoo.com", "89765341567"
    )
)


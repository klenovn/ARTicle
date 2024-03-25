package com.comrades.article.controllers

import com.comrades.article.models.User
import com.comrades.article.providers.Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ControllerDatabase {
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
                Provider.database.add(User(nickname = username, email = email, password = password))

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

    fun enter(
        email: String,
        password: String,
        callback: (result: Boolean, error: Throwable?) -> Unit
    ) {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            delay(2000L)

            try {
                var flag = false
//                var rand = (0..10).random()
//
//                if (rand % 4 == 0)
//                    throw Exception("DatabaseException")
                for (user in Provider.database) {
                    if (user.email == email && user.password == password) {
                        flag = true
                        break
                    }
                }


                if (flag)
                    withContext(Dispatchers.Main) {
                        callback(true, null)
                    }

                if (!flag)
                    withContext(Dispatchers.Main) {
                        callback(false, null)
                    }

            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    callback(false, e)
                }
            }

        }
    }

    fun exists(
        email: String,
        username: String,
        callback: (result: Pair<Boolean, Boolean>) -> Unit
    ) {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            delay(1000L)
            var result = true to true

            for (user in Provider.database) {
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

}
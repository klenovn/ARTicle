package com.comrades.article

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.intellij.lang.annotations.RegExp

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        val editTextEmail = findViewById<EditText>(R.id.textEmailAddress)
        val editTextUsername = findViewById<EditText>(R.id.textUsername)
        val editTextPassword = findViewById<EditText>(R.id.textPassword)
        val editTextRepeatedPassword = findViewById<EditText>(R.id.textRepeatedPassword)

    }

    fun isValidField(fieldParam: String): Boolean {
        return true
    }


    companion object{
        val regExpPhone = Regex("^((\\+?7)|8)[0-9]{10}\$")
        val regExpEmail = Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+\$")
    }
}
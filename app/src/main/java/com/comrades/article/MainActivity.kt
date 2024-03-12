package com.comrades.article

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_activity)

        val btnIn = findViewById<Button>(R.id.btn1)
        val btnReg = findViewById<Button>(R.id.btn2)

        val editTextEmail = findViewById<EditText>(R.id.textEmailAddress)
        val editTextPassword =  findViewById<EditText>(R.id.textPassword)


        btnIn.setOnClickListener {
            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()
            if (email == EMAIL && password == PASSWORD){
                Toast.makeText(
                    applicationContext,
                    TOAST_STR,
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        btnReg.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    companion object{
        val EMAIL = "111"
        val PASSWORD = "000"
        val TOAST_STR = "Вы ввели верный логин"
    }
}
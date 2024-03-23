package com.comrades.article

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AuthorizationActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_activity)

        val btnIn = findViewById<Button>(R.id.btn1)
        val btnReg = findViewById<Button>(R.id.btn2)

        val editTextEmail = findViewById<EditText>(R.id.textEmailAddress)
        val editTextPassword =  findViewById<EditText>(R.id.textPassword)
        val inputLayoutEmail = findViewById<GridLayout>(R.id.inputLayoutEmail)
        val inputLayoutPassword = findViewById<GridLayout>(R.id.inputLayoutPassword)
        val hiddenErrorMessage = findViewById<TextView>(R.id.hiddenError)

        btnIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            ControllerDatabase.enter(email, password){ result: Boolean, error: Throwable? ->
                if (result){
                    inputLayoutEmail.background = applicationContext.getDrawable(R.drawable.input_layout_success)
                    inputLayoutPassword.background =  applicationContext.getDrawable(R.drawable.input_layout_success)
                    hiddenErrorMessage.visibility = View.INVISIBLE
                    val intent = Intent(applicationContext, MainMenuActivity::class.java)
                    startActivity(intent)
                }
                else if (error == null){
                    inputLayoutEmail.background = applicationContext.getDrawable(R.drawable.input_layout_error)
                    inputLayoutPassword.background =  applicationContext.getDrawable(R.drawable.input_layout_error)
                    hiddenErrorMessage.visibility = View.VISIBLE
                }
                else {
                    Toast.makeText(
                        applicationContext,
                        "TOAST_STR",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

        btnReg.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}
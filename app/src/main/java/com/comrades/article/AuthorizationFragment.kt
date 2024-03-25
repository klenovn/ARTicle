package com.comrades.article

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController

class AuthorizationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)
        val btnIn = view.findViewById<Button>(R.id.btn1)
        val btnReg = view.findViewById<Button>(R.id.btn2)
        val textForgot = view.findViewById<TextView>(R.id.textForgot)

        val editTextEmail = view.findViewById<EditText>(R.id.textEmailAddress)
        val editTextPassword = view.findViewById<EditText>(R.id.textPassword)
        val inputLayoutEmail = view.findViewById<GridLayout>(R.id.inputLayoutEmail)
        val inputLayoutPassword = view.findViewById<GridLayout>(R.id.inputLayoutPassword)
        val hiddenErrorMessage = view.findViewById<TextView>(R.id.hiddenError)

        btnIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            ControllerDatabase.enter(email, password) { result: Boolean, error: Throwable? ->
                if (result) {
                    inputLayoutEmail.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.input_layout_success)
                    inputLayoutPassword.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.input_layout_success)
                    hiddenErrorMessage.visibility = View.INVISIBLE
                    val intent = Intent(context, MainMenuActivity::class.java)
                    startActivity(intent)
                } else if (error == null) {
                    inputLayoutEmail.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.input_layout_error)
                    inputLayoutPassword.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.input_layout_error)
                    hiddenErrorMessage.visibility = View.VISIBLE
                } else {
                    Toast.makeText(
                        context,
                        "TOAST_STR",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

        btnReg.setOnClickListener {
            val intent = Intent(context, RegistrationActivity::class.java)
            startActivity(intent)
        }

        textForgot.setOnClickListener{
            view.findNavController().navigate(R.id.action_authorizationFragment_to_recoveryFragment)
        }

        return view
    }
}
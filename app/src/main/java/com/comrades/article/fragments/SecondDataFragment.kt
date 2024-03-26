package com.comrades.article.fragments

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
import com.comrades.article.R
import com.comrades.article.controllers.ControllerDatabase
import com.comrades.article.utilities.isValidField

class SecondDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_data, container, false)
        val btnRegistration = view.findViewById<Button>(R.id.btn2)
        val editTextPassword = view.findViewById<EditText>(R.id.textPassword)
        val editTextRepeatedPassword = view.findViewById<EditText>(R.id.textRepeatedPassword)
        val hiddenErrorPassword = view.findViewById<TextView>(R.id.hiddenErrorPassword)
        val hiddenErrorRepeatedPassword =
            view.findViewById<TextView>(R.id.hiddenErrorRepeatedPassword)
        val email = SecondDataFragmentArgs.fromBundle(requireArguments()).email
        val username = SecondDataFragmentArgs.fromBundle(requireArguments()).username
        val layoutPassword = view.findViewById<GridLayout>(R.id.inputLayoutPassword)
        val layoutRepeatedPassword = view.findViewById<GridLayout>(R.id.inputLayoutRepeatedPassword)

        btnRegistration.setOnClickListener {
            var password = editTextPassword.text.toString()
            var repeatedPassword = editTextRepeatedPassword.text.toString()
            val flagField1 = isValidField(password, fieldPosition = 3)
            val flagField2 = isValidField(password, repeatedPassword, 4)
            when {
                flagField1 && flagField2 -> {
                    layoutPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_success
                    )
                    layoutRepeatedPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_success
                    )
                    hiddenErrorPassword.visibility = View.VISIBLE
                    hiddenErrorPassword.text =
                        ContextCompat.getString(
                            requireContext(),
                            R.string.valid
                        )
                    hiddenErrorPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    hiddenErrorRepeatedPassword.visibility = View.VISIBLE
                    hiddenErrorRepeatedPassword.text =
                        ContextCompat.getString(requireContext(), R.string.valid)
                    hiddenErrorRepeatedPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    ControllerDatabase.add(
                        email,
                        username,
                        password
                    ) { result: Boolean, error: Throwable? ->
                        if (result) {
                            view.findNavController()
                                .navigate(R.id.action_secondDataFragment_to_successRegistrationFragment)
                        } else
                            Toast.makeText(
                                context,
                                TOAST_STR,
                                Toast.LENGTH_LONG
                            ).show()

                    }
                }

                !flagField1 -> {
                    layoutPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_error
                    )
                    layoutRepeatedPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_error
                    )
                    hiddenErrorPassword.visibility = View.VISIBLE
                    hiddenErrorPassword.text =
                        ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    hiddenErrorRepeatedPassword.visibility = View.VISIBLE
                    hiddenErrorRepeatedPassword.text =
                        ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorRepeatedPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }

                flagField1 && !flagField2 -> {
                    layoutPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_success
                    )
                    layoutRepeatedPassword.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_error
                    )
                    hiddenErrorRepeatedPassword.visibility = View.VISIBLE
                    hiddenErrorRepeatedPassword.text =
                        ContextCompat.getString(requireContext(), R.string.no_matching)
                    hiddenErrorRepeatedPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    hiddenErrorPassword.visibility = View.VISIBLE
                    hiddenErrorPassword.text =
                        ContextCompat.getString(requireContext(), R.string.valid)
                    hiddenErrorPassword.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }
            }
        }
        return view
    }


    companion object {
        val TOAST_STR = "Ошибка сети. Данные не были загружены в систему."
    }
}
package com.comrades.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController

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
        val hiddenErrorRepeatedPassword = view.findViewById<TextView>(R.id.hiddenErrorRepeatedPassword)



        btnRegistration.setOnClickListener {
            val flagField1 = isValidField(editTextPassword.text.toString(), fieldPosition = 3)
            val flagField2 = isValidField(editTextPassword.text.toString(), editTextRepeatedPassword.text.toString(), 4)
            when {
                flagField1 && flagField2 -> view.findNavController()
                    .navigate(R.id.action_secondDataFragment_to_successRegistrationFragment)

                !flagField1 -> {
                    hiddenErrorPassword.visibility = View.VISIBLE
                    hiddenErrorPassword.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorPassword.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    hiddenErrorRepeatedPassword.visibility = View.VISIBLE
                    hiddenErrorRepeatedPassword.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorRepeatedPassword.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
                flagField1 && !flagField2 -> {
                    hiddenErrorRepeatedPassword.visibility = View.VISIBLE
                    hiddenErrorRepeatedPassword.text = ContextCompat.getString(requireContext(), R.string.no_matching)
                    hiddenErrorRepeatedPassword.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    hiddenErrorPassword.visibility = View.VISIBLE
                    hiddenErrorPassword.text = ContextCompat.getString(requireContext(), R.string.valid)
                    hiddenErrorPassword.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }
            }
        }
        return view
    }
}
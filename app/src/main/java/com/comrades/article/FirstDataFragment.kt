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

class FirstDataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first_data, container, false)
        val btnNextStep = view.findViewById<Button>(R.id.btn1)
        val editTextEmail = view.findViewById<EditText>(R.id.textEmailAddress)
        val editTextUsername = view.findViewById<EditText>(R.id.textUsername)
        val hiddenErrorEmail = view.findViewById<TextView>(R.id.hiddenErrorEamil)
        val hiddenErrorUsername = view.findViewById<TextView>(R.id.hiddenErrorUsername)

        btnNextStep.setOnClickListener {
            val flagField1 = isValidField(editTextEmail.text.toString(), fieldPosition = 1)
            val flagField2 = isValidField(editTextUsername.text.toString(), fieldPosition = 2)
            when {
                flagField1 && flagField2 -> view.findNavController()
                    .navigate(R.id.action_firstDataFragment_to_secondDataFragment)

                !flagField1 && flagField2 -> {
                    hiddenErrorEmail.visibility = View.VISIBLE
                    hiddenErrorEmail.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    hiddenErrorUsername.visibility = View.VISIBLE
                    hiddenErrorUsername.text = ContextCompat.getString(requireContext(), R.string.valid)
                    hiddenErrorUsername.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }
                flagField1 && !flagField2 -> {
                    hiddenErrorUsername.visibility = View.VISIBLE
                    hiddenErrorUsername.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorUsername.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    hiddenErrorEmail.visibility = View.VISIBLE
                    hiddenErrorEmail.text = ContextCompat.getString(requireContext(), R.string.valid)
                    hiddenErrorEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                }
                !flagField1 && !flagField2 -> {
                    hiddenErrorUsername.visibility = View.VISIBLE
                    hiddenErrorUsername.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorUsername.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    hiddenErrorEmail.visibility = View.VISIBLE
                    hiddenErrorEmail.text = ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
        }
        return view
    }

}

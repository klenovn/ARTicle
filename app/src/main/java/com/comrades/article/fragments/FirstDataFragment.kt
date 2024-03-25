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
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.comrades.article.R
import com.comrades.article.controllers.ControllerDatabase
import com.comrades.article.utilities.isValidField

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
        val layoutEmail = view.findViewById<GridLayout>(R.id.inputLayoutEmail)
        val layoutUsername = view.findViewById<GridLayout>(R.id.inputLayoutUsername)

        btnNextStep.setOnClickListener {
            val email = editTextEmail.text.toString()
            val username = editTextUsername.text.toString()
            val flagField1 = isValidField(email, fieldPosition = 1)
            val flagField2 = isValidField(username, fieldPosition = 2)
            when {
                flagField1 && flagField2 -> {
                    ControllerDatabase.exists(email, username) {
                        when {
                            it.first && it.second -> {
                                layoutEmail.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_success
                                )
                                layoutUsername.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_success
                                )
                                hiddenErrorUsername.visibility = View.VISIBLE
                                hiddenErrorUsername.text =
                                    ContextCompat.getString(
                                        requireContext(),
                                        R.string.valid
                                    )
                                hiddenErrorUsername.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                                hiddenErrorEmail.visibility = View.VISIBLE
                                hiddenErrorEmail.text =
                                    ContextCompat.getString(requireContext(), R.string.valid)
                                hiddenErrorEmail.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                                val action =
                                    FirstDataFragmentDirections.actionFirstDataFragmentToSecondDataFragment(
                                        email,
                                        username
                                    )
                                view.findNavController()
                                    .navigate(action)
                            }

                            !it.first -> {
                                layoutEmail.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_error
                                )
                                layoutUsername.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_success
                                )
                                hiddenErrorEmail.visibility = View.VISIBLE
                                hiddenErrorEmail.text =
                                    ContextCompat.getString(requireContext(), R.string.user_exists)
                                hiddenErrorEmail.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.red
                                    )
                                )
                                hiddenErrorUsername.visibility = View.VISIBLE
                                hiddenErrorUsername.text =
                                    ContextCompat.getString(requireContext(), R.string.valid)
                                hiddenErrorUsername.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )

                            }

                            it.first && !it.second -> {
                                layoutEmail.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_success
                                )
                                layoutUsername.background = ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.input_layout_error
                                )
                                hiddenErrorUsername.visibility = View.VISIBLE
                                hiddenErrorUsername.text =
                                    ContextCompat.getString(
                                        requireContext(),
                                        R.string.username_exists
                                    )
                                hiddenErrorUsername.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.red
                                    )
                                )
                                hiddenErrorEmail.visibility = View.VISIBLE
                                hiddenErrorEmail.text =
                                    ContextCompat.getString(requireContext(), R.string.valid)
                                hiddenErrorEmail.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.green
                                    )
                                )
                            }
                        }

                    }
                }

                !flagField1 && flagField2 -> {
                    ControllerDatabase.exists(email, username) {
                        if (!it.second) {
                            layoutEmail.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorEmail.visibility = View.VISIBLE
                            hiddenErrorEmail.text =
                                ContextCompat.getString(requireContext(), R.string.invalid)
                            hiddenErrorEmail.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                            layoutUsername.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorUsername.visibility = View.VISIBLE
                            hiddenErrorUsername.text =
                                ContextCompat.getString(
                                    requireContext(),
                                    R.string.username_exists
                                )
                            hiddenErrorUsername.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                        } else {
                            layoutEmail.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorEmail.visibility = View.VISIBLE
                            hiddenErrorEmail.text =
                                ContextCompat.getString(requireContext(), R.string.invalid)
                            hiddenErrorEmail.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                            layoutUsername.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_success
                            )
                            hiddenErrorUsername.visibility = View.VISIBLE
                            hiddenErrorUsername.text =
                                ContextCompat.getString(
                                    requireContext(),
                                    R.string.valid
                                )
                            hiddenErrorUsername.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.green
                                )
                            )
                        }
                    }
                }

                flagField1 && !flagField2 -> {
                    ControllerDatabase.exists(email, username) {
                        if (!it.first) {
                            layoutUsername.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorUsername.visibility = View.VISIBLE
                            hiddenErrorUsername.text =
                                ContextCompat.getString(requireContext(), R.string.invalid)
                            hiddenErrorUsername.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                            layoutEmail.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorEmail.visibility = View.VISIBLE
                            hiddenErrorEmail.text =
                                ContextCompat.getString(
                                    requireContext(),
                                    R.string.user_exists
                                )
                            hiddenErrorEmail.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                        } else {
                            layoutUsername.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_error
                            )
                            hiddenErrorUsername.visibility = View.VISIBLE
                            hiddenErrorUsername.text =
                                ContextCompat.getString(requireContext(), R.string.invalid)
                            hiddenErrorUsername.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.red
                                )
                            )
                            layoutEmail.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.input_layout_success
                            )
                            hiddenErrorEmail.visibility = View.VISIBLE
                            hiddenErrorEmail.text =
                                ContextCompat.getString(
                                    requireContext(),
                                    R.string.valid
                                )
                            hiddenErrorEmail.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.green
                                )
                            )
                        }
                    }

                }

                !flagField1 && !flagField2 -> {
                    layoutEmail.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_error
                    )
                    layoutUsername.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.input_layout_error
                    )
                    hiddenErrorUsername.visibility = View.VISIBLE
                    hiddenErrorUsername.text =
                        ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorUsername.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    hiddenErrorEmail.visibility = View.VISIBLE
                    hiddenErrorEmail.text =
                        ContextCompat.getString(requireContext(), R.string.invalid)
                    hiddenErrorEmail.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }
            }
        }
        return view
    }

}

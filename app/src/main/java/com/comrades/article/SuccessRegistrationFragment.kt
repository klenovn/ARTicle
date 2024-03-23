package com.comrades.article

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat

class SuccessRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_success_registration, container, false)
        val btnNextStep = view.findViewById<Button>(R.id.btnMenu)

        btnNextStep.setOnClickListener {
            val intent = Intent(context, AuthorizationActivity::class.java)
            startActivity(intent)
        }


        return view
    }

}
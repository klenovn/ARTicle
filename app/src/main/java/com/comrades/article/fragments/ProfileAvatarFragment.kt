package com.comrades.article.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.comrades.article.R

class ProfileAvatarFragment(): Fragment() {

    private lateinit var avatarImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.profile_avatar_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarImage = view.findViewById(R.id.fragment_avatar_image)
        arguments?.let { avatarImage.setImageResource(it.getInt("avatarId")) }

        view.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }


}
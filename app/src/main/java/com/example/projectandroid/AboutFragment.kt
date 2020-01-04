package com.example.projectandroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = inflater.inflate(R.layout.fragment_about, container, false)
        (activity as MainActivity).setToolbarTitle(context!!.getString(R.string.about))

        return binding
    }



}

package com.example.projectandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.projectandroid.databinding.RegisterFragmentBinding

class Register : Fragment() {

    companion object {
        fun newInstance() = Register()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setToolbarTitle("Register")
        val binding = DataBindingUtil.inflate<RegisterFragmentBinding>(inflater,
            R.layout.register_fragment, container, false)

        binding.linkLogin.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_register_to_login)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

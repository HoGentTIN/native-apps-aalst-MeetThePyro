package com.example.projectandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.projectandroid.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.login_fragment.*

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    // private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater,
            R.layout.login_fragment, container, false)

        binding.btnLogin.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_homeFragment)
        }
        binding.linkSignup.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_register)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

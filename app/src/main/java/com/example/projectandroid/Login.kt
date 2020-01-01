package com.example.projectandroid

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.projectandroid.databinding.LoginFragmentBinding

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
        (activity as MainActivity).setToolbarTitle("Login")
        val navigationDrawer = activity!!.findViewById<DrawerLayout>(R.id.drawer_layout)
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater,
            R.layout.login_fragment, container, false)

        binding.btnLogin.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_homeFragment)
            navigationDrawer.setDrawerLockMode(LOCK_MODE_UNLOCKED)
        }
        binding.linkSignup.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_login_to_register)
        }


        navigationDrawer.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)

        //(activity as AppCompatActivity).supportActionBar?.


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

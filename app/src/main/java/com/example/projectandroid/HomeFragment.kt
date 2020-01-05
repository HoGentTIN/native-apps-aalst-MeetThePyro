package com.example.projectandroid

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.home_fragment.home_getTop_btn
import kotlinx.android.synthetic.main.home_fragment.home_no_network
import kotlinx.android.synthetic.main.home_fragment.home_timeSpan_switch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.home_fragment, container, false)
        (activity as MainActivity).setToolbarTitle("Home")

        return binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var request: String = "top100forever"
        super.onActivityCreated(savedInstanceState)
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm.activeNetwork != null) {
            home_no_network.visibility = GONE
            home_timeSpan_switch.isEnabled = true
            // home_timeSpan_switch.isChecked = (activity as MainActivity).request == "top100in2weeks"
            home_timeSpan_switch.isChecked = request == "top100in2weeks"
        } else {
            home_no_network.visibility = VISIBLE
            home_timeSpan_switch.isChecked = false
            home_timeSpan_switch.isEnabled = false
            request = "top100forever"
        }

        home_timeSpan_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                request = "top100in2weeks"
            } else {
                // The switch is disabled
                request = "top100forever"
            }
        }

        home_getTop_btn.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameOverviewFragment(request))
        }
    }
}

package com.example.projectandroid

// import kotlinx.android.synthetic.main.game_overview_fragment.gameList_view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.data.database.GameDatabase
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
import java.net.URL
import kotlinx.android.synthetic.main.game_overview_fragment.gameList_offline
import kotlinx.android.synthetic.main.list_item_games.view.game_appid

class GameOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverviewFragment()
    }

    private lateinit var viewModel: GameOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var request = ""

        arguments?.let {
            val safeArgs = GameOverviewFragmentArgs.fromBundle(it)
            request = safeArgs.request
        }
        (activity as MainActivity).setToolbarTitle("Top 100 Games | $request")
        var _appid: String

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val binding = GameOverviewFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao
        val viewModelFactory = GameOverviewViewModelFactory(dataSource, cm)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameOverviewViewModel::class.java)

        binding.gameViewModel = viewModel

        //ar request = (activity as MainActivity).request
        /*if (request == "top100forever") {
            (activity as MainActivity).setToolbarTitle("Top 100 Games | Forever")
        } else if (request == "top100in2weeks") {
            (activity as MainActivity).setToolbarTitle("Top 100 Games | 2 Weeks")
        } else {
            (activity as MainActivity).setToolbarTitle("Error")
        }*/

        //viewModel.getTop100((activity as MainActivity).request)
        viewModel.getTop100(request)

        val adapter = GameAdapter()
        binding.gameListView.adapter = adapter

        adapter.onItemClick = { pos, view ->

            _appid = view.game_appid.text.toString()
            val bundle = Bundle()
            bundle.putString("appid", _appid)
            (activity as MainActivity).selectGame(_appid)
        }

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)
        // TODO: Use the ViewModel
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm.activeNetwork != null) {
            gameList_offline.visibility = GONE
        } else {
            gameList_offline.visibility = VISIBLE
        }
    }
}

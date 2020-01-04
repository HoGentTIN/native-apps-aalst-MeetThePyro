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
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.data.database.GameDatabase
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
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

        val timespan = if (request == "top100in2weeks") {
            context!!.getString(R.string.timespan_2weeks)
        } else {
            context!!.getString(R.string.timepsan_forever)
        }
        (activity as MainActivity).setToolbarTitle(context!!.getString(R.string.top_100_title, timespan))
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

        viewModel.getTop100(request)

        val adapter = GameAdapter()
        binding.gameListView.adapter = adapter

        adapter.onItemClick = { pos, view ->

            _appid = view.game_appid.text.toString()
            val bundle = Bundle()
            bundle.putString("appid", _appid)
            Navigation.findNavController(view).navigate(GameOverviewFragmentDirections.actionGameOverviewFragmentToGameFragment(_appid))
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

package com.example.projectandroid

//import kotlinx.android.synthetic.main.game_overview_fragment.gameList_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
import com.example.projectandroid.model.Game
import kotlinx.android.synthetic.main.list_item_games.view.game_appid
import java.net.URL


class GameOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverviewFragment()
    }

    private lateinit var viewModel: GameOverviewViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setToolbarTitle("Top 100 Games")
        var _games: List<Game>
        var _appid: String
        //return inflater.inflate(R.layout.game_overview_fragment, container, false)
        //val binding = DataBindingUtil.inflate<GameOverviewFragmentBinding>(inflater,
        //    R.layout.game_overview_fragment, container, false)

        val binding = GameOverviewFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)

        binding.gameViewModel = viewModel

        var request = (activity as MainActivity).request
        if(request == "top100forever"){
            (activity as MainActivity).setToolbarTitle("Top 100 Games | Forever")
        } else if(request == "top100in2weeks") {
            (activity as MainActivity).setToolbarTitle("Top 100 Games | 2 Weeks")
        } else {
            (activity as MainActivity).setToolbarTitle("Error")
        }

        viewModel.setRequest((activity as MainActivity).request)
        viewModel.getTop100((activity as MainActivity).request)

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


        /* binding.gameListView.game_card.setOnClickListener {
                view: View ->
            (activity as MainActivity).selectGame()
        }*/


        //viewModel.games.observe(this, Observer {

        //})
        return binding.root
    }

    //private fun selectGame(gameCard: MaterialCardView?) {
    //    view?.findNavController().navigate(R.id.)
    //}

    fun getJsonFromURL(wantedURL: String): String {
        return URL(wantedURL).readText()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)
        // TODO: Use the ViewModel
        /*overview_timeSpan_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                viewModel.getTop100("topbork")
            } else {
                // The switch is disabled
                viewModel.getTop100("top100forever")
            }
        }*/


    }
}

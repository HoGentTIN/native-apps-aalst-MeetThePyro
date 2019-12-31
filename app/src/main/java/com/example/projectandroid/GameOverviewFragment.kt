package com.example.projectandroid

//import kotlinx.android.synthetic.main.game_overview_fragment.gameList_view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
import com.example.projectandroid.model.Game
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.content_main.toolbar_title
import kotlinx.android.synthetic.main.content_main.view.toolbar_title
import kotlinx.android.synthetic.main.list_item_games.view.game_appid
import kotlinx.android.synthetic.main.list_item_games.view.game_card
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

        val adapter = GameAdapter()
        binding.gameListView.adapter = adapter

        adapter.onItemClick = { pos, view ->
            _appid = view.game_appid.text.toString()
            (activity as MainActivity).selectGame(_appid)
        }

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let{
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

    fun getJsonFromURL(wantedURL: String) : String {
        return URL(wantedURL).readText()
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)
        // TODO: Use the ViewModel
    }*/


}

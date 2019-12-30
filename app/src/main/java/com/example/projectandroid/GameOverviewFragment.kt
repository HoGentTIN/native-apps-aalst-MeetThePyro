package com.example.projectandroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.data.adapters.GameListAdapter
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
import com.example.projectandroid.databinding.LoginFragmentBinding
import com.example.projectandroid.model.Game
//import kotlinx.android.synthetic.main.game_overview_fragment.gameList_view
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
        var _games: List<Game>
        //return inflater.inflate(R.layout.game_overview_fragment, container, false)
        //val binding = DataBindingUtil.inflate<GameOverviewFragmentBinding>(inflater,
        //    R.layout.game_overview_fragment, container, false)

        val binding = GameOverviewFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)

        binding.gameViewModel = viewModel

        val adapter = GameAdapter()
        binding.gameListView.adapter = adapter

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })




    //viewModel.games.observe(this, Observer {

    //})



        return binding.root
    }

     fun getJsonFromURL(wantedURL: String) : String {
        return URL(wantedURL).readText()
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)
        // TODO: Use the ViewModel
    }*/


}

package com.example.projectandroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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



        //var test = URL("https://steamspy.com/api.php?request=top100forever").readText()
//var test: String
//test = getJsonFromURL("https://steamspy.com/api.php?request=top100forever")

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



        //binding


        //val result = URL("http://api.steampowered.com/ISteamApps/GetAppList/v2").readText()

        //val result = URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=STEAMKEY&format=json").readText()
    }*/


}

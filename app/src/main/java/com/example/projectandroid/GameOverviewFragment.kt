package com.example.projectandroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.game_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameOverviewViewModel::class.java)
        // TODO: Use the ViewModel

        val result = URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/?key=STEAMKEY&format=json").readText()
    }

}

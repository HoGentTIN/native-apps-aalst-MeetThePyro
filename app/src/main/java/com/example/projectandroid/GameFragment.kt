package com.example.projectandroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.projectandroid.data.adapters.GameAdapter
import com.example.projectandroid.databinding.GameOverviewFragmentBinding
import kotlinx.android.synthetic.main.game_fragment.game_detailed_desc


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = GameFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel

        val adapter = GameAdapter()
        binding.gameListView.adapter = adapter

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel


    }

}

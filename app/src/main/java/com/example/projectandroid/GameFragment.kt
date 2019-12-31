package com.example.projectandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.projectandroid.data.adapters.GameDetailedAdapter
import com.example.projectandroid.databinding.GameFragmentBinding


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setToolbarTitle("Game")
        val binding = GameFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gameDetailedViewModel = viewModel

        val adapter = GameDetailedAdapter()
        binding.gameListDetailedView.adapter = adapter

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel


    }

}

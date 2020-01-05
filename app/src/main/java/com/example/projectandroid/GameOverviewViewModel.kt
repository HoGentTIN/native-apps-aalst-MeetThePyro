package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.data.repository.GameRepository
import com.example.projectandroid.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameOverviewViewModel(
    private var gameRepository: GameRepository
) : ViewModel() {
    // TODO: Implement the

    private val _properties = MutableLiveData<List<Game>>()
    val properties: LiveData<List<Game>>
        get() = _properties

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getTop100(request: String) {
        coroutineScope.launch {
            _properties.value = gameRepository.getTop100(request)
        }
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

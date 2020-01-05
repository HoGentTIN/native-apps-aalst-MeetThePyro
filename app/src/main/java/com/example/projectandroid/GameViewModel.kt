package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.data.repository.GameDetailedRepository
import com.example.projectandroid.model.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class GameViewModel : ViewModel() {
    private var _appid: String = "218620"

    private var gameDetailedRepository: GameDetailedRepository = GameDetailedRepository()

    private val _properties = MutableLiveData<List<Data>>()
    val properties: LiveData<List<Data>>
        get() = _properties

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {

        getGame()
    }

    fun getGame() {
        coroutineScope.launch {

            // Get the Deferred object for our Retrofit request
            _properties.value = gameDetailedRepository.getGameDetails(_appid)
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

    fun setAppid(appid: String) {
        _appid = appid
    }
}

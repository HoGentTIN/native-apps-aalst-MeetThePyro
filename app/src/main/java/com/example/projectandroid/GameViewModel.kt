package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.model.Data
import com.example.projectandroid.model.Game
import com.example.projectandroid.model.GameDetailed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class SteamApiStatus { LOADING, ERROR, DONE }

class GameViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    private val _status = MutableLiveData<SteamApiStatus>()

    //private var gameRepository: GameRepository = GameRepository()

    // The external immutable LiveData for the response String
    val response: LiveData<SteamApiStatus>
        get() = _status

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

    private fun getGame() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = SteamApi.retrofitService.getGame()
            try {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()
                _status.value = SteamApiStatus.DONE
                var firstGame = listResult.values.first().data
                _properties.value = listOf(firstGame)
            } catch (e: Exception) {
                val error = e.message
                _status.value = SteamApiStatus.ERROR
                //_properties.value = ArrayList()
            }
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
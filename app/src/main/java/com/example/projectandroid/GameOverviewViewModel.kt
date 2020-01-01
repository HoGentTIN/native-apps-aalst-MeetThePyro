package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class GameApiStatus { LOADING, ERROR, DONE }

class GameOverviewViewModel : ViewModel() {
    // TODO: Implement the
    private val _response = MutableLiveData<String>()
    private val _status = MutableLiveData<GameApiStatus>()
    private var _appid = String
    private var _request = "top100forever"

    //private var gameRepository: GameRepository = GameRepository()

    // The external immutable LiveData for the response String
    val response: LiveData<GameApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Game>>()
    val properties: LiveData<List<Game>>
        get() = _properties


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getTop100()
    }

    private fun getTop100() {
        coroutineScope.launch {

            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = GameApi.retrofitService.getTop100(_request)
            try {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()
                _status.value = GameApiStatus.DONE
                _properties.value = listResult.values.toList()
                //adapter = GameListAdapter(this, properties.value)
            } catch (e: Exception) {
                val error = e.message
                _status.value = GameApiStatus.ERROR
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

    /* private suspend fun getTop100Games(){
         //_response.value = "Set the API response here!"
         GameApi.retrofitService.getTop100().enqueue(
             object: Callback<List<Game>> {
                 override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                     _response.value = "Failure: " + t.message
                 }

                 override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                     _response.value = "Success: ${response.body()?.size} Games retrieved"
                 }
             })
     }
 */


    /* private val viewModelJob = SupervisorJob()
     private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
     init {
         viewModelScope.launch {
             try {
                 gameRepository.getGames()
             } catch (e: Exception) {

             }
         }
     }

     val games = gameRepository.games*/
}

package com.example.projectandroid

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.data.database.GameDatabaseDao
import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class GameApiStatus { LOADING, ERROR, DONE }

class GameOverviewViewModel(
    val database: GameDatabaseDao,
    val cm: ConnectivityManager
) : ViewModel() {
    // TODO: Implement the

    private val _status = MutableLiveData<GameApiStatus>()

    // private var gameRepository: GameRepository = GameRepository()

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

    fun getTop100(request: String) {

        coroutineScope.launch {
            if (cm.activeNetwork == null) {
                // var test = getTop100FromDatabase()
                _properties.value = getTop100FromDatabase()
            } else {
                getTop100FromApi(request)
            }
        }
    }

    private suspend fun getTop100FromDatabase(): List<Game>? {
        return withContext(Dispatchers.IO) {
            var games = database.getAll()
            // if (games.value == null || games.value!!.isEmpty())
            games
        }
    }

    private suspend fun getTop100FromApi(request: String) {

            var getPropertiesDeferred = GameApi.retrofitService.getTop100(request)
            try {
                withContext(Dispatchers.IO) {
                    // Await the completion of our Retrofit request
                    var listResult = getPropertiesDeferred.await()
                    database.clear()
                    database.insertAll(listResult.values.toList())

                    withContext(Dispatchers.Main) {
                        _status.value = GameApiStatus.DONE
                        // _properties.value = getTop100FromDatabase()?.toMutableList()
                        _properties.value = listResult.values.toList()
                    }
                }
            } catch (e: Exception) {
                _status.value = GameApiStatus.ERROR
            }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
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

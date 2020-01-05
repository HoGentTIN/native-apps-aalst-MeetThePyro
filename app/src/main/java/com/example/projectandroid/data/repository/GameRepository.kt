package com.example.projectandroid.data.repository

import android.net.ConnectivityManager
import com.example.projectandroid.data.database.GameDatabaseDao
import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepository(
    val database: GameDatabaseDao,
    val cm: ConnectivityManager
) {
    suspend fun getTop100(request: String): List<Game>? {
        return if (cm.activeNetwork == null) {
            // var test = getTop100FromDatabase()
            getTop100FromDatabase()
        } else {
            getTop100FromApi(request)
        }
    }

    private suspend fun getTop100FromDatabase(): List<Game>? {
        return withContext(Dispatchers.IO) {
            var games = database.getAll()
            // if (games.value == null || games.value!!.isEmpty())
            games
        }
    }

    private suspend fun getTop100FromApi(request: String): List<Game>? {

        var getPropertiesDeferred = GameApi.retrofitService.getTop100(request)
        return try {
            withContext(Dispatchers.IO) {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()
                database.clear()
                database.insertAll(listResult.values.toList())
                listResult.values.toList()
            }
        }catch (e:Error){
            null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}

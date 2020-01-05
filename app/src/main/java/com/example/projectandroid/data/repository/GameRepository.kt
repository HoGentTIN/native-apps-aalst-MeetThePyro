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
        // if the device is not connected to a network
        return if (cm.activeNetwork == null) {
            getTop100FromDatabase()
        } else {
            getTop100FromApi(request)
        }
    }

    private suspend fun getTop100FromDatabase(): List<Game>? {
        return withContext(Dispatchers.IO) {
            val games = database.getAll()
            games
        }
    }

    private suspend fun getTop100FromApi(request: String): List<Game>? {

        val getPropertiesDeferred = GameApi.retrofitService.getTop100(request)

        // Try to get the details from the API, and if that fails, return null
        return try {
            withContext(Dispatchers.IO) {
                // Await the completion of our Retrofit request
                val listResult = getPropertiesDeferred.await()
                database.clear()
                database.insertAll(listResult.values.toList())
                listResult.values.toList()
            }
        } catch (e: Error) {
            null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}

package com.example.projectandroid.data.repository

import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.model.Data
import com.example.projectandroid.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GameDetailedRepository {
    suspend fun getGameDetails(appid: String): List<Data>? {
        var getPropertiesDeferred = SteamApi.retrofitService.getGame(appid)

        return try {
            withContext(Dispatchers.IO) {

                var listResult = getPropertiesDeferred.await()
                var firstGame = listResult.values.first().data
                listOf(firstGame)

            }
        } catch (e: Exception) {
            null
        }
    }
}

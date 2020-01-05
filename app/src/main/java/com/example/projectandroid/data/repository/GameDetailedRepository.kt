package com.example.projectandroid.data.repository

import com.example.projectandroid.SteamApiStatus
import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameDetailedRepository {
    suspend fun getGameDetails(appid: String): List<Data> {
        var getPropertiesDeferred = SteamApi.retrofitService.getGame(appid)

        return withContext(Dispatchers.IO) {
            var listResult = getPropertiesDeferred.await()
            var firstGame = listResult.values.first().data
            listOf(firstGame)
        }
    }
}
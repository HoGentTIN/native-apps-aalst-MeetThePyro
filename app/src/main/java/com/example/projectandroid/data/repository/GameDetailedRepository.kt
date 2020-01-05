package com.example.projectandroid.data.repository

import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.model.Data
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameDetailedRepository {
    suspend fun getGameDetails(appid: String): List<Data>? {
        val getPropertiesDeferred = SteamApi.retrofitService.getGame(appid)

        //Try to get the details from the API, and if that fails, return null
        return try {
            withContext(Dispatchers.IO) {

                val listResult = getPropertiesDeferred.await()
                val firstGame = listResult.values.first().data
                listOf(firstGame)
            }
        } catch (e: Exception) {
            null
        }
    }
}

package com.example.projectandroid

import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.data.network.GameApiService
import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.data.network.SteamApiService
import com.example.projectandroid.model.Game
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiTests {
    private lateinit var gameApi: GameApiService
    private lateinit var steamApi: SteamApiService
    @Before
    fun get_api_services() {
        gameApi = GameApi.retrofitService
        steamApi = SteamApi.retrofitService
    }

    @Test
    fun testsWork() {
        Assert.assertTrue(true)
    }

    @Test
    fun gameApi_returns_list_with_size_100() {
        runBlocking {
            val response = gameApi.getTop100("top100forever")
            Assert.assertEquals(100, response.await().values.size)
        }
    }

    @Test
    fun gameApi_returns_objects_of_class_game() {
        runBlocking {
            val response = gameApi.getTop100("top100forever")
            Assert.assertEquals(Game::class.java, response.await().values.first()::class.java)
        }
    }

    @Test
    fun steamApi_gives_game_with_correct_id() {
        runBlocking {
            val response = steamApi.getGame("440")
            Assert.assertEquals(440, response.await().values.first().data.steam_appid)
        }
    }
}
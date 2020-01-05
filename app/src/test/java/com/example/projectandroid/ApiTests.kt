package com.example.projectandroid

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.projectandroid.data.database.GameDatabase
import com.example.projectandroid.data.database.GameDatabaseDao
import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.data.network.SteamApi
import com.example.projectandroid.model.Game
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ApiTests {

    @Test
    fun testsWork() {
        Assert.assertTrue(true)
    }

    @Test
    fun gameApi_gives_100_games() {
        runBlocking {
            val api = GameApi.retrofitService
            val response = api.getTop100("top100forever")
            Assert.assertEquals(100, response.await().values.size)

        }
    }

    @Test
    fun gameApi_returns_objects_of_class_game(){
        runBlocking {
            val api = GameApi.retrofitService
            val response = api.getTop100("top100forever")
            Assert.assertEquals(Game::class.java, response.await().values.first()::class.java)
        }

    }

    @Test
    fun steamApi_gives_game_with_correct_id() {
        runBlocking {
            val api = SteamApi.retrofitService
            val response = api.getGame("440")
            Assert.assertEquals(440, response.await().values.first().data.steam_appid)
        }
    }

}

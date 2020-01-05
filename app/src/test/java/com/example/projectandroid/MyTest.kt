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

class MyTest {

    @Test
    fun testsWork() {
        Assert.assertTrue(true)
    }

    @Test
    fun gameApi_gives_response() {
        val api = GameApi.retrofitService
        val response = api.getTop100("top100forever")
        Assert.assertNotNull(response)
    }

    @Test
    fun steamApi_gives_response() {
        val api = SteamApi.retrofitService
        val response = api.getGame("440")
        Assert.assertNotNull(response)
    }

}

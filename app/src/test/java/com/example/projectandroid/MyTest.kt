package com.example.projectandroid

import com.example.projectandroid.data.network.GameApi
import com.example.projectandroid.data.network.SteamApi
import org.junit.Assert
import org.junit.Test

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

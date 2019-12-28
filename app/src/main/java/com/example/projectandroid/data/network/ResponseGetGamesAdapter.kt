package com.example.projectandroid.data.network

import com.example.projectandroid.model.Game
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class ResponseGetGamesAdapter {
    @ToJson
    fun arrayListToJson(list: ArrayList<Game>) : List<Game> = list

    @FromJson
    fun arrayListFromJson(list: List<Game>) : ArrayList<Game> = ArrayList(list)

}

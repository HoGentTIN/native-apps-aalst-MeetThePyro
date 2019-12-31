package com.example.projectandroid.model

import com.squareup.moshi.Json
import java.util.*


data class Game(
    @field:Json(name = "appid")
    var appid:Int,
    @field:Json(name = "name")
    var name:String,
    var developer: String,
    var publisher: String

)


package com.example.projectandroid.model

import com.squareup.moshi.Json
import java.util.*


data class Game(
    //var id:Int,
    @field:Json(name = "appid")
    var appid:Int,
    @field:Json(name = "name")
    var name:String
    //var desc:String
)


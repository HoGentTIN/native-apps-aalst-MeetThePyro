package com.example.projectandroid.model

import com.squareup.moshi.Json

data class GameDetailed(
    @field:Json(name = "steam_appid")
    var appid:Int,
    @field:Json(name = "name")
    var name:String,
    var is_free:Boolean,
    var header_image:String,
    var website:String,
    var short_description:String,
    var price_overview: PriceOverview
)

package com.example.projectandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "game_top_table")
data class Game(
    @PrimaryKey
    @field:Json(name = "appid")
    var appid: Int,
    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    var name: String,
    @ColumnInfo(name = "developer")
    var developer: String,
    @ColumnInfo(name = "publisher")
    var publisher: String

)

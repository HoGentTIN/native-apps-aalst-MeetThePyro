package com.example.projectandroid.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projectandroid.model.Game

@Dao
interface GameDatabaseDao {
    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Query("SELECT * FROM game_top_table WHERE appid = :appid")
    fun get(appid: Int): Game?

    @Query("SELECT * FROM game_top_table")
    fun getAll(): LiveData<List<Game>>

    @Query("DELETE FROM game_top_table")
    fun clear()
}
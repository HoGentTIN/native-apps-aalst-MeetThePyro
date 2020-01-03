package com.example.projectandroid.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.projectandroid.model.Game

@Dao
interface GameDatabaseDao {
    @Transaction
    fun insertAll(kist: List<Game>) = kist.forEach { insert(it) }

    @Insert
    fun insert(game: Game)

    @Update
    fun update(game: Game)

    @Query("SELECT * FROM game_top_table WHERE appid = :appid")
    fun get(appid: Int): Game?

    @Query("SELECT * FROM game_top_table")
    fun getAll(): List<Game>

    @Query("DELETE FROM game_top_table")
    fun clear()
}

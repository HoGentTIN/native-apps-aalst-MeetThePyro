package com.example.projectandroid

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.projectandroid.data.database.GameDatabase
import com.example.projectandroid.data.database.GameDatabaseDao
import com.example.projectandroid.model.Game
import java.io.IOException
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class DbTest {
    private lateinit var gameDao: GameDatabaseDao
    private lateinit var db: GameDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory wordDao because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, GameDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        gameDao = db.gameDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetGame() {
        // We have to specify the ID here or we can't test for object equality in assert due to
        // autoGeneration of the ID
        val game = Game(999, "test", "testDev", "testPublisher")
        // val word = Word("Test", 1L)
        runBlocking {
            gameDao.insert(game)
            val allGames = gameDao.getAll()
            Assert.assertTrue(allGames.contains(game))
        }
    }
}

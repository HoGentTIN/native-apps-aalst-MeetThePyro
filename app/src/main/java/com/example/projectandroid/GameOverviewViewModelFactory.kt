package com.example.projectandroid

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.data.database.GameDatabaseDao

class GameOverviewViewModelFactory (
    private val dataSource: GameDatabaseDao,
    private val application: Application,
    private val cm : ConnectivityManager) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameOverviewViewModel::class.java)) {
            return GameOverviewViewModel(dataSource, application, cm) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

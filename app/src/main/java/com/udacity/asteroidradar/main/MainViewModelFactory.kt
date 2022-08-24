package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.database.AsteroidDatabaseDao

class MainViewModelFactory(val dao: AsteroidDatabaseDao, val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("uncheck_cast")
            return MainViewModel(dao, app) as T
        }
        throw IllegalArgumentException("Error creating mainViewModel")
    }
}
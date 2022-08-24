package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidDatabaseDao
import com.udacity.asteroidradar.database.PictureOfTheDay
import com.udacity.asteroidradar.repository.Repository

import java.time.LocalDateTime

class MainViewModel(val database: AsteroidDatabaseDao, application: Application) : AndroidViewModel(application) {

    // database
    private val data = AsteroidDatabase.getInstance(application)

    // repository
    private val repository = Repository(data)

    var asteroids: LiveData<List<Asteroid>> = repository.asteroids
    val pictureOfTheDay: LiveData<PictureOfTheDay> = repository.potd

    // Navigation from main screen to detail screen
    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid>()
    val navigateToAsteroidDetail
        get() = _navigateToAsteroidDetail

    // onClick function when an asteroid is clicked
    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }

    // finish onClick function when asteroid is clicked
    fun onAsteroidDetailNavigated() {
        _navigateToAsteroidDetail.value = null
    }

    // Function for when menu option changes
    fun onMenuOptionChange(menuOption: String) {
        asteroids = when(menuOption) {
            "week" -> repository.asteroids
            "today" -> repository.todayAsteroids
            else -> repository.asteroids
        }
    }
}
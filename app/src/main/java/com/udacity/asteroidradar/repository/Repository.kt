package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.PictureOfTheDay
import com.udacity.asteroidradar.network.asDatabaseModel
import com.udacity.asteroidradar.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.time.LocalDateTime

class Repository(private val database: AsteroidDatabase) {
    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDatabaseDao.getAllAsteroids()) {
        it.asDomainModel()
    }

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            try {
                val start = LocalDateTime.now().toString().substring(0, 10)
                val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

                val results = NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY)
                val asteroidsResults = parseAsteroidsJsonResult(JSONObject(results))

                database.asteroidDatabaseDao.insertAll(asteroidsResults.asDatabaseModel())

                Log.i("insertion", "Success! added asteroids to the database")

            } catch (e: Exception) {
                Log.e("error", "Repository: error retrieving data from API $e")
            }
        }
    }

    suspend fun refreshPOTD(): PictureOfTheDay {
        val placeholder = PictureOfTheDay("https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg", "placeHolder", "No new Image for today!")

        val results = NasaApi.retrofitService.getPictureOfTheDay(Constants.API_KEY)

        if (results.mediaType != "image") {
            return placeholder
        }

        return results
    }
}
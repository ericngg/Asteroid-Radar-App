package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class Repository(private val database: AsteroidDatabase) {
    private val _asteroids = MutableLiveData<List<Asteroid>>()

    val asteroids
        get() = _asteroids

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            try {
                val start = LocalDateTime.now().toString().substring(0, 10)
                val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

                val results = NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY)
                val asteroidsResults = parseAsteroidsJsonResult(JSONObject(results))

                _asteroids.value = asteroidsResults
                database.asteroidDatabaseDao.insertAll(asteroidsResults.asDatabaseModel())

                Log.i("insertion", "Success! added asteroids to the database")

            } catch (e: Exception) {
                Log.e("error", "Repository: error retrieving data from API $e")
            }
        }
    }

    private fun getAsteroids() {

        val start = LocalDateTime.now().toString().substring(0, 10)
        val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

        try {


            Log.i("insertion", "Success! added asteroids to the database")

        } catch (e: Exception) {
            Log.i("insertion", "Error on inserting data " + e)
        }
    }
}
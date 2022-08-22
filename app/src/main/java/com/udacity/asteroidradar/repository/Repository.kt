package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.network.NetworkAsteroids
import com.udacity.asteroidradar.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class Repository(private val database: AsteroidDatabase) {
    /*
    private val _asteroids = MutableLiveData<List<Asteroid>>()

    val asteroids
        get() = _asteroids

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            getAsteroids()
        }
    }

    private fun getAsteroids() {

        val start = LocalDateTime.now().toString().substring(0, 10)
        val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

        try {
            val results = NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY)

            val asteroidsResults = parseAsteroidsJsonResult(JSONObject(results))

            Log.i("testing", asteroidsResults.toString())

            _asteroids.value = asteroidsResults

            val asteroidList = asteroidsResults.map {
                NetworkAsteroids(
                    it.id,
                    it.codename,
                    it.closeApproachDate,
                    it.absoluteMagnitude,
                    it.estimatedDiameter,
                    it.relativeVelocity,
                    it.distanceFromEarth,
                    it.isPotentiallyHazardous
                )
            }

            database.asteroidDatabaseDao.insert(*asteroidList.asDatabaseModel().toTypedArray())

            Log.i("insertion", "Success! added asteroids to the database")

        } catch (e: Exception) {
            Log.i("insertion", "Error on inserting data " + e)
        }
    } */
}
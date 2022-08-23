package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidDatabaseDao
import com.udacity.asteroidradar.database.PictureOfTheDay
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class MainViewModel(val database: AsteroidDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val data = AsteroidDatabase.getInstance(application)
    private val repository = Repository(data)

    val asteroids: LiveData<List<Asteroid>> = repository.asteroids

    private val _pictureOfTheDay = MutableLiveData<PictureOfTheDay>()
    val pictureOfTheDay: LiveData<PictureOfTheDay>
        get() = _pictureOfTheDay


    init {
        //getAsteroids()
        //getPictureOfTheDay()

        viewModelScope.launch {
            repository.refreshData()
            _pictureOfTheDay.value = repository.refreshPOTD()
        }
    }
/*
    private fun getAsteroids() {
        val start = LocalDateTime.now().toString().substring(0, 10)
        val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

        viewModelScope.launch {
            try {
                var listResult = NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY)
                Log.i("test", listResult)
                _asteroids.value = parseAsteroidsJsonResult(JSONObject(listResult))

                Log.i("test", (_asteroids.value as ArrayList<Asteroid>)[0].toString())
            } catch (e: Exception) {
                Log.e("error", "Failure to retrieve data from api $e")
            }
        }

        /*
        NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _asteroids.value = parseAsteroidsJsonResult(JSONObject(response.body()))

                Log.i("success", "Data retrieved successfully")
                Log.i("success", (_asteroids.value as ArrayList<Asteroid>)[0].toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("error", "Failure to retrieve data from api $t")
            }
        })

         */

        //val results = NasaApi.retrofitService.getAsteroids(start, end, Constants.API_KEY)
        //val asteroidsResults = parseAsteroidsJsonResult(JSONObject(results))
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            try {
                val list = NasaApi.retrofitService.getPictureOfTheDay(Constants.API_KEY)
            } catch (e: Exception) {
                Log.e("error", "error retrieving picture of the day $e")
            }
        }

        /*
        NasaApi.retrofitService.getPictureOfTheDay(Constants.API_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val json = JSONObject(response.body())
                _pictureOfTheDayUrl.value = ""

                if (json.getString("media_type").equals("image")) {
                    _pictureOfTheDayUrl.value = json.getString("url")
                }

                _pictureOfTheDayUrl.value = "https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg"
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("error", "Failure to retrieve picture of the day $t")
            }
        })
         */
    }
 */

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid>()
    val navigateToAsteroidDetail
        get() = _navigateToAsteroidDetail

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }

    fun onAsteroidDetailNavigated() {
        _navigateToAsteroidDetail.value = null
    }
}
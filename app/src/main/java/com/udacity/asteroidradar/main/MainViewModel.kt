package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    private val _asteroids = MutableLiveData<List<Asteroid>>()

    val asteroids
        get() = _asteroids

    init {
        getAsteroids()
    }

    private fun getAsteroids() {

        val start = LocalDateTime.now().toString().substring(0, 10)
        val end = LocalDateTime.now().plusDays(7).toString().substring(0, 10)

        NasaApi.retrofitService.getProperties(start, end, Constants.API_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("success", "Successful fetching data")

                val result = JSONObject(response.body())

                _asteroids.value = parseAsteroidsJsonResult(result)

                Log.i("success", (_asteroids.value as ArrayList<Asteroid>)[0].toString())

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", "Error fetching data $t")
            }

        })

    }
}
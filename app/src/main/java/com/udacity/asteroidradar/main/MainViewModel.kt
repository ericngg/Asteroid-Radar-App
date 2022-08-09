package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    init {
        getNasaProperties()
    }

    private fun getNasaProperties() {
        NasaApi.retrofitService.getProperties(Constants.TEST_START_DATE, Constants.TEST_END_DATE, Constants.API_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("success", "Successful fetching data")

                Log.i("success", "Success: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", "Error fetching data $t")
            }

        })

        /*
        try {
            var listResult = NasaApi.retrofitService.getProperties(Constants.TEST_START_DATE, Constants.TEST_END_DATE, Constants.API_KEY)

            Log.i("success", "Successful fetching data")

            Log.i("success", listResult.toString())
        } catch (e: Exception){
            Log.i("error", "Error fetching data $e")
        }
         */
    }
}
package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureOfTheDayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(potd: PictureOfTheDay)

    @Query("DElETE FROM picture_of_the_day_table")
    suspend fun clear()

    @Query("SELECT * FROM picture_of_the_day_table ORDER BY media_type DESC LIMIT 1")
    fun getPictureOfTheDay(): LiveData<PictureOfTheDay>
}
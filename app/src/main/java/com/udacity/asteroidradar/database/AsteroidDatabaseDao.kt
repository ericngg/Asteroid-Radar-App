package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AsteroidDatabaseDao {
    @Insert
    fun insert(asteroid: Asteroid)

    @Update
    fun update(asteroid: Asteroid)

    @Query("SELECT * from asteroid_table WHERE id = :key")
    fun get(key: Long): Asteroid?

    @Query("DELETE FROM asteroid_table")
    fun clear()

    @Query("SELECT * FROM asteroid_table ORDER BY date(close_approach_date) ASC")
    fun getAllAsteroids(): List<Asteroid>

}

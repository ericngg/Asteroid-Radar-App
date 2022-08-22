package com.udacity.asteroidradar.database

import androidx.room.*


@Dao
interface AsteroidDatabaseDao {
    @Update
    fun update(asteroid: Asteroid)

    @Query("SELECT * from asteroid_table WHERE id = :key")
    fun get(key: Long): Asteroid?

    @Query("DELETE FROM asteroid_table")
    fun clear()

    @Query("SELECT * FROM asteroid_table ORDER BY date(close_approach_date) ASC")
    fun getAllAsteroids(): List<Asteroid>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<Asteroid>)

}

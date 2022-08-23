package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.network.AsteroidDB


@Dao
interface AsteroidDatabaseDao {
    @Update
    fun update(asteroid: AsteroidDB)

    @Query("SELECT * from asteroid_table WHERE id = :key")
    fun get(key: Long): AsteroidDB?

    @Query("DELETE FROM asteroid_table")
    fun clear()

    @Query("SELECT * FROM asteroid_table ORDER BY date(close_approach_date) ASC")
    fun getAllAsteroids(): LiveData<List<AsteroidDB>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidDB>)

}

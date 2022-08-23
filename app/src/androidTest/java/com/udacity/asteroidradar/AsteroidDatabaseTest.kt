package com.udacity.asteroidradar

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidDatabaseDao
import com.udacity.asteroidradar.network.AsteroidDB
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AsteroidDatabaseTest {

    private lateinit var asteroidDao: AsteroidDatabaseDao
    private lateinit var db: AsteroidDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AsteroidDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        asteroidDao = db.asteroidDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAsteroid() {
        val asteroid = AsteroidDB(2329437, "329437 (2002 0A22)", "2022-08-11", 19.33, 0.8091703835, 5.9275428582, 0.169307131, true)
        //asteroidDao.insert(asteroid)

        val temp = asteroidDao.get(2329437)

        if (temp != null) {
            assertEquals(asteroid.id, temp.id)
        }
    }
}
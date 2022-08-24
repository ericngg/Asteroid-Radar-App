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
        val asteroid = AsteroidDB(id=2004034, codename="4034 Vishnu (1986 PA)", closeApproachDate="2022-08-24", absoluteMagnitude=18.41, estimatedDiameter=1.2360612132, relativeVelocity=9.3961448096, distanceFromEarth=0.3143421481, isPotentiallyHazardous=true)
        asteroidDao.insert(asteroid)

        val temp = asteroidDao.getTodayAsteroids("2022-08-24")

        if (temp != null) {
            temp.value?.get(0)?.let { assertEquals(asteroid.id, it.id) }
        }
    }
}
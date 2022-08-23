package com.udacity.asteroidradar.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    var id: Long = 0L,

    val codename: String,

    val closeApproachDate: String,

    val absoluteMagnitude: Double,

    val estimatedDiameter: Double,

    val relativeVelocity: Double,

    val distanceFromEarth: Double,

    val isPotentiallyHazardous: Boolean) : Parcelable
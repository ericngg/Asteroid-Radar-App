package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity (tableName = "picture_of_the_day_table")
data class PictureOfTheDay constructor(
    @PrimaryKey
    val url: String,

    @Json(name = "media_type")
    @ColumnInfo(name = "media_type")
    val mediaType: String,

    @ColumnInfo(name = "title")
    val title: String
)
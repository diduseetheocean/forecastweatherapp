package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.daily

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class TempData (
    @PrimaryKey
    @field:Json(name = "day")
    val day: Float? = 0.0f,
    @field:Json(name = "min")
    val min: Float? = 0.0f,
    @field:Json(name = "max")
    val max: Float? = 0.0f,
    @field:Json(name = "night")
    val night: Float? = 0.0f,
    @field:Json(name = "eve")
    val eve: Float? = 0.0f,
    @field:Json(name = "morn")
    val morn: Float? = 0.0f,
)
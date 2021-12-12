package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.hourly

import com.squareup.moshi.Json

data class HourlyRain (
    @field:Json(name = "1h")
    val oneHour: Float? = 0.0f,
)
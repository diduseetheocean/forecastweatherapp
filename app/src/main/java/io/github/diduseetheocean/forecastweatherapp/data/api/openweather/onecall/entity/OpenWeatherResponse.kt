package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity

import com.squareup.moshi.Json
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.daily.DailyData
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.hourly.HourlyData

data class OpenWeatherResponse(
    @field:Json(name = "lat")
    val lat: Float? = 0.0f,
    @field:Json(name = "lon")
    val lon: Float? = 0.0f,
    @field:Json(name = "timezone")
    val timezone: String? = "",
    @field:Json(name = "timezone_offset")
    val timezone_offset: String? = "",
    @field:Json(name = "hourly")
    val hourly: List<HourlyData>? = listOf(),
    @field:Json(name = "daily")
    val daily: List<DailyData>? = listOf(),
)
package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity

import com.squareup.moshi.Json

data class WeatherData (
    @field:Json(name = "id")
    val id: Int? = 0,
    @field:Json(name = "main")
    val main: String? = "",
    @field:Json(name = "description")
    val description: String? = "",
    @field:Json(name = "icon")
    val icon: String? = "",
)
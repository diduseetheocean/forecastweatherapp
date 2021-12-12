package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.hourly

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.WeatherData

data class HourlyData (
    @field:Json(name = "dt")
    val dt: Long? = 0,
    @field:Json(name = "temp")
    val temp: Float? = 0.0f,
    @field:Json(name = "feels_like")
    val feels_like: Float? = 0.0f,
    @field:Json(name = "pressure")
    val pressure: Int? = 0,
    @field:Json(name = "humidity")
    val humidity: Int? = 0,
    @field:Json(name = "dew_point")
    val dew_point: Float? = 0.0f,
    @field:Json(name = "uvi")
    val uvi: Float? = 0.0f,
    @field:Json(name = "clouds")
    val clouds: Int? = 0,
    @field:Json(name = "visibility")
    val visibility: Int? = 0,
    @field:Json(name = "wind_speed")
    val wind_speed: Float? = 0.0f,
    @field:Json(name = "wind_deg")
    val wind_deg: Int? = 0,
    @field:Json(name = "wind_gust")
    val wind_gust: Float? = 0.0f,
    @field:Json(name = "weather")
    val weather: List<WeatherData>? = listOf(),
    @field:Json(name = "rain")
    val rain: HourlyRain? = HourlyRain(),
    @field:Json(name = "pop")
    val pop: Float? = 0.0f,
)
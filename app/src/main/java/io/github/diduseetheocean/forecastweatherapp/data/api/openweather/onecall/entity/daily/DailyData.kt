package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.daily

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.WeatherData

data class DailyData (
    @field:Json(name = "dt")
    val dt: Long? = 0,

    @field:Json(name = "sunrise")
    val sunrise: Int? = 0,
    @field:Json(name = "sunset")
    val sunset: Int? = 0,
    @field:Json(name = "moonrise")
    val moonrise: Int? = 0,
    @field:Json(name = "moonset")
    val moonset: Int? = 0,
    @field:Json(name = "moon_phase")
    val moon_phase: Float? = 0.0f,

    @field:Json(name = "temp")
    val temp: TempData? = TempData(),
    @field:Json(name = "feels_like")
    val feels_like: FeelsLikeData? = FeelsLikeData(),

    @field:Json(name = "pressure")
    val pressure: Int? = 0,
    @field:Json(name = "humidity")
    val humidity: Int? = 0,
    @field:Json(name = "dew_point")
    val dew_point: Float? = 0.0f,
    @field:Json(name = "wind_speed")
    val wind_speed: Float? = 0.0f,
    @field:Json(name = "wind_deg")
    val wind_deg: Int? = 0,
    @field:Json(name = "wind_gust")
    val wind_gust: Float? = 0.0f,
    @field:Json(name = "weather")
    val weather: List<WeatherData>? = listOf(),
    @field:Json(name = "clouds")
    val clouds: Int? = 0,
    @field:Json(name = "pop")
    val pop: Float? = 0.0f,
    @field:Json(name = "rain")
    val rain: Float? = 0.0f,
    @field:Json(name = "uvi")
    val uvi: Float? = 0.0f,
)
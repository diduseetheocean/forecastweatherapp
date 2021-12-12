package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.conversion

import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.hourly.HourlyData
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherHourlyModel

fun HourlyData.toWeatherHourlyModel() = WeatherHourlyModel(
    temp = temp ?: 0.0f,
    rain = rain?.oneHour ?: 0.0f,
    datetime = dt ?: 0,
    icon = weather?.get(0)?.icon ?: "",
)
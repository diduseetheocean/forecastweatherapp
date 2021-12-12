package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.conversion

import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.daily.DailyData
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherDailyModel

fun DailyData.toWeatherDailyModel() = WeatherDailyModel(
    tempDay = temp?.day ?: 0.0f,
    tempNight = temp?.night ?: 0.0f,
    description = weather?.get(0)?.description ?: "",
    datetime = dt ?: 0,
    icon = weather?.get(0)?.icon ?: "",
)
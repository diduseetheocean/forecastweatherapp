package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.conversion

import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.OpenWeatherResponse
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel

fun OpenWeatherResponse.toWeatherLocationModel() = WeatherLocationModel(
    weatherDailyModelList = daily?.map { it.toWeatherDailyModel() } ?: emptyList(),
    weatherHourlyModelList = hourly?.map { it.toWeatherHourlyModel() } ?: emptyList()
)